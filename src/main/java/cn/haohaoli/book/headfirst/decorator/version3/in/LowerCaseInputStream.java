package cn.haohaoli.book.headfirst.decorator.version3.in;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author LiWenHao
 * @date 2019-03-12 20:37
 */
public class LowerCaseInputStream extends FilterInputStream {


    /**
     * Creates a <code>FilterInputStream</code>
     * by assigning the  argument <code>in</code>
     * to the field <code>this.in</code> so as
     * to remember it for later use.
     *
     * @param in the underlying input stream, or <code>null</code> if
     *           this instance is to be created without an underlying stream.
     */
    public LowerCaseInputStream(InputStream in) {
        super(in);
    }

    @Override
    public int read() throws IOException {
        int c = super.read();
        return c == -1 ? c : Character.toLowerCase((char) c);
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        int read = super.read(b, off, len);
        for (int i = 0; i < read; i++) {
            b[i] = (byte) Character.toLowerCase((char) b[i]);
        }
        return read;
    }
}
