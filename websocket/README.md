##### spring-boot-starter-websocket
利用 <code>spring-boot-starter-websocket</code> 实现一个简单的智能人机对话。  
一个是基于浏览器支持的 WebSocket 实现的，页面是 templates/index.html.  
访问路径： http://localhost:8080   
一个是基于sockJs实现的， 页面是 templates/sockjs.html.  
访问路径: http://localhost:8080/sockjs    

业务简单思路:  
使用Map模拟简单的题库，接收问题后查找 Map.key ，如果能部分匹配上的话把 Map.value 作为答案返回，如果匹配不上的话则直接返回问答描述不正确。
如果客户端在一分钟后都没有继续提问，则服务端主动向客户端发送一条消息，并关闭连接。