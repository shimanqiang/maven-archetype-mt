# maven-archetype-mt
https://837062099.iteye.com/blog/2312813
https://blog.csdn.net/jeikerxiao/article/details/60324029
https://blog.csdn.net/wfl_137724/article/details/77592309

# 创建maven骨架
1、创建工程

      添加配置、目录结构、默认代码等
      
2、配置插件
```
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-archetype-plugin</artifactId>
    <version>3.0.1</version>
</plugin>
```
3、生成骨架

    打开cmd窗口，cd到刚才创建的maven project的根目录，运行maven命令： 
    mvn archetype:create-from-project 
    然后会在target目录下面生成generated-sources目录，这个就是生成的 archetype
    
4、安装到本地仓库

    cd进入generated-sourced/archetype目录，运行maven命令： 
    mvn install 
    这样就把自定义的archetype安装到本地仓库了。 
    archetype安装的地址是在maven安装目录下面的conf/settings.xml文件中指定的(<localRepository>字节)。 
    默认会在  ~/.m2  目录下面生成一个archetype-catalog.xml文件（和默认的settings.xml在同一个目录），
    声明了该archetype的groupId、artifactId和其他属性。 
    因为Eclipse创建maven项目过程中，选择的“Default Local”指向的地址就是 ~/.m2， 
    所以文件archetype-catalog.xml会被eclipse自动读取， 
    使用eclipse创建maven项目的时候可以在"Default Local"一项中找到刚才自定义archetype名字。
    
5、使用自定义的archetype-cmd

    mvn archetype:generate
        -DarchetypeCatalog=local
        -DarchetypeRepository=local
        -DarchetypeGroupId=com.sankuai.meituan.demo  ## 骨架的
        -DarchetypeArtifactId=demo-archetype  ##骨架的
        -DarchetypeVersion=1.0  ##骨架版本号
        -DinteractiveMode=false
        -DgroupId=com.xxxx.appdemo  #新建立项目的
        -DartifactId=maven-demo     #新建立项目的
        
    --------------------- 
    demo-archetype = 创建骨架工程的<artifactId>demo</artifactId> 拼接上 -archetype
    
    
    eg.
    mvn archetype:generate -DarchetypeCatalog=local -DarchetypeRepository=local -DarchetypeGroupId=com.sankuai.meituan.demo -DarchetypeArtifactId=demo-archetype -DarchetypeVersion=1.0 -DinteractiveMode=false -DgroupId=com.xxxx.appdemo -DartifactId=maven-demo 
    
6、使用自定义的archetype-ide
    