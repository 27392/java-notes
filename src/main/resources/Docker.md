# Docker

 > 常用命令
   
   + 查看本地镜像
    
     `docker images`
     
   + 删除本地镜像
      
      `docker image rm 镜像id`
    
   + 查看运行中的容器
    
     `docker ps`
   
   + 查看所有的容器
       
     `docker ps -a`
     
   + 启动一个存在的容器
       
     `docker run [OPTIONS]`
     
        + OPTIONS
        
            - `--name` : 为容器指定一个名称
            
            - `-e` : username="ritchie": 设置环境变量
            
            - `-d` : 后台运行容器，并返回容器ID
            
            - `-P` : 随机端口映射，容器内部端口随机映射到主机的高端口
            
            - `-p` : 指定端口映射，格式为：主机(宿主)端口:容器端口
     
   + 启动一个存在的容器
       
     `docker start 容器id`
     
   + 停止容器
       
     `docker stop 容器id`
          
   + 删除容器
       
     `docker rm 容器id`
     
### Mysql 安装

---

   + 拉取镜像   
       
    docker pull mysql:5.7

   + 运行命令
    
    docker run -p 3306:3306 --name masterMysql -e MYSQL_ROOT_PASSWORD=mysql123321 -d mysql:5.7

### Jenkins 安装

---

   + 拉取镜像   
    
    docker pull jenkins/jenkins:lts

   + 运行命令

    docker run -p 8080:8080 -p 50000:50000 -d 
    -v /home/jenkins:/var/jenkins_home   // 
    -v /usr/local/java:/usr/local/java   // 挂载java
    -v /usr/local/maven:/usr/local/maven // 挂载maven
    jenkins/jenkins:lts
