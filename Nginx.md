# Nginx
    
   参考：https://www.jianshu.com/p/5a57d1bea859
   
   参考：https://www.cnblogs.com/piscesLoveCc/p/5867900.html

### 安装依赖
    
+ `yum install gcc-c++`

    安装 nginx 需要先将官网下载的源码进行编译，编译依赖 gcc 环境
    
+ `yum install -y pcre pcre-devel` 
    
    PCRE(Perl Compatible Regular Expressions) 是一个Perl库，包括 perl 兼容的正则表达式库。nginx 的 http 模块使用 pcre 来解析正则表达式，所以需要在 linux 上安装 pcre 库，pcre-devel 是使用 pcre 开发的一个二次开发库。nginx也需要此库        

+ `yum install -y zlib zlib-devel`
    
    zlib 库提供了很多种压缩和解压缩的方式， nginx 使用 zlib 对 http 包的内容进行 gzip
    
+ `yum install -y openssl openssl-devel`

    OpenSSL 是一个强大的安全套接字层密码库，囊括主要的密码算法、常用的密钥和证书封装管理功能及 SSL 协议，并提供丰富的应用程序供测试或其它目的使用。
    nginx 不仅支持 http 协议，还支持 https（即在ssl协议上传输http）
  
### 安装
    
+ 下载  
    
    nginx 官网 https://nginx.org/en/download.html
    
    - `wget -c https://nginx.org/download/nginx-1.16.0.tar.gz` 
    
         -  `-c` 断点续传
    
+ 解压
    
    - `tar -zxvf nginx-1.16.0.tar.gz`
        
        - `-z` :是否同时具有gzip的属性，即是否需要用gzip压缩
        
        - `-x` :从归档文件中解析文件
        
        - `-v` :压缩过程中显示文件
        
        - `-f` :使用文件名
+ 配置
    
    - `cd nginx-1.16.0`
        
        进入文件
        
    - `./configure`
        
        普通配置
    
    - `./configure --with-http_ssl_module`
        
        如果需要配置https

+ 编译安装        

    - `make`
    
    - `make install`

### 开机自动
    
+ `cd /lib/systemd/system`

+ `vim nginx.service`

+ ```shell
   [Unit]
   Description=nginx
   After=network.target
   
   [Service]
   Type=forking
   ExecStart=/usr/local/nginx/sbin/nginx
   ExecReload=/usr/local/nginx/sbin/nginx -s reload
   ExecStop=/usr/local/nginx/sbin/nginx -s quit
   PrivateTmp=true
   
   [Install]
   WantedBy=multi-user.target
   ```
   
+ 命令

    - 启动nginx服务
    
        `systemctl start nginx.service`
        
    - 设置开机自启动
    
        `systemctl enable nginx.service`
    
    - 停止开机自启动
        
        `systemctl disable nginx.service`
    
    - 查看服务当前状态 
        
        `systemctl status nginx.service`
    
    - 重新启动服务 
        
        `systemctl restart nginx.service`

### 配置HTTPS    
        