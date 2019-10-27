import org.junit.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;

/**
 * @author LiWenHao
 * @date 2019/10/27 16:13
 */
public class TCPTest {

    private final int PORT = 7777;

    @Test
    public void server() {
        try (ServerSocket serverSocket = new ServerSocket(PORT);
             Socket socket = serverSocket.accept()) {
            System.out.println("接收到客户端连接");
            try (InputStream inputStream = socket.getInputStream();
                 ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {

                byte[] buff = new byte[1024];
                int len;
                while ((len = inputStream.read(buff)) != -1) {
                    byteArrayOutputStream.write(buff, 0, len);
                }
                byte[] fileByte = byteArrayOutputStream.toString().toLowerCase().getBytes();

                try (OutputStream outputStream = socket.getOutputStream()) {
                    outputStream.write(fileByte);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void client() throws IOException {
        InetAddress inetAddress = InetAddress.getLocalHost();
        byte[] buff;
        int len;
        try (Socket socket = new Socket(inetAddress, PORT);
             OutputStream outputStream = socket.getOutputStream()) {
            // 发送文件
            try (InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("ExampleFile.txt")) {
                Objects.requireNonNull(resourceAsStream);
                buff = new byte[1024];
                while ((len = resourceAsStream.read(buff)) != -1) {
                    outputStream.write(buff, 0, len);
                }
            }
            // 告诉客户端不在发送数据,否则会阻塞
            socket.shutdownOutput();

            File file = new File("xx.txt");
            try (InputStream inputStream = socket.getInputStream();
                 ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                 FileOutputStream fileOutputStream = new FileOutputStream(file)) {
                while ((len = inputStream.read(buff)) != -1) {
                    byteArrayOutputStream.write(buff, 0, len);
                }
                fileOutputStream.write(byteArrayOutputStream.toByteArray());
            }

            // 删除文件必须在关闭流之后,否则文件被其他程序占用无法继续操作
            boolean delete = file.delete();
            if (!delete) {
                System.out.println("删除文件失败");
            }
        }

    }
}
