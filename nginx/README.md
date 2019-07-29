<h1>INTEGRATING NGINX</H1>

<p>1) Just install the Ubuntu distro's nginx.  It is all you need for what we are doing. </p>

<p>2) Place the "default" file in this folder into nginx's sites-enabled directory.  It sets up a basic nginx server that reverse proxies for socket-attached Nodejs server processes.  This will come in real handy.</p>

<p>3) Once you've confirmed The Nginx server is running and configured, you link it to the project as follows:  First, if you haven't already, clone this project to a local folder.  Next, find the full file path to the project's /client/pub directory.  Next, cd to /var/www on your system and delete the /html directory.  Then, from within the /var/www directory type <pre><code>$ ln -s "full path to the client/pub directory" html</code></pre>

To see if all's well, open a web browser (I Develop on Chrome Firefox) and point it to <a href="http://localhost/welcome">http://localhost/welcome"</a>.  If you see the banner you've arrived.  Now check to see if you can talk to the browser repl <a href="https://github.com/tecumsehcommunications/StarlinkViewer/tree/master/repls#the-client-browser-repl">here</a>.</p> 
