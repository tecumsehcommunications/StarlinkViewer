<h1>Reverse Proxy Bliss</h1>
<p>The Server Side of our app has two main processes:  1) An Nginx server listening on the all the right sockets.  It serves static and streaming content, where necessary, and passes websocket traffic to the second process: 2) a Nodjs server. If you haven't already, you should configure an Nginx as described <a href="">here</a>.</p>

<p> I don't expect to make many changes to the Nginx server configuration in order to achieve a full proof of concept for this project.  The Nodejs side of things, however, Needs a lot of work.</p>

As a starting point, you will "make sure gitignroe on node-modules directories



