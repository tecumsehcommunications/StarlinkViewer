<h1>Nginx + NodeJS</h1>
<p>The Server Side of our project comes in two parts:  Nginx and Node </p>
<h2>Nginx</h2>
<p>The Nginx part will become more important as the project moves into production.  For now, we just use it to 1) serve/stream static content to the browser, and 2) reverse proxy socket traffic to Nodejs.</p>

<h3>Installing Nginx</h3>

<p>1) Just install the Ubuntu distro's nginx.  It is all you need for what we are doing. </p>

<p>2) Replace the contents of the "default" file in nginx's sites-available directory with the contents of the "nginx-config" file in the /server/dev directory of this project.</p>

<p>3) On restart Nginx should be up and running and all that remains to do is give it static content to serve.  </p>

<p>4) If you have not already, clone this project to a local folder. Then, determine the full file path to the project's /client/pub directory.  Next, cd to /var/www on your system and delete the /html directory.  Then, from within the /var/www directory type <pre><code>$ ln -s "full path to the client/pub directory" html</code></pre>Now, whatever changes you make to the client side of this app, they will be immediately effective on the Server. </p> 

<p>To see if all's well, open a web browser on the same machine and point it to <a href="http://localhost">http://localhost" </a>.If the Starlink Viewer page loads, you've arrived. </p>  


<h2>Node</h2>

<h3>Why Node?</h3>
<h4>The Node server of this project is slated to do the following things in production:</h4>

<p>1) Periodically update local satellite orbital data via the api provided by space-track.org</p>

<p>2) Handle all the websocket traffic with the Client in the browser</p>

<p>3) Serve dynamic content as needed</p>

<p>In Development mode (meaning, we are just running things out of a repl) Node is also used as the livereload server to assist development on the Client.</p>

<p>Look at the core.cljs file in the /server/src/server/ directory of this project to see where we are at with the development.</p>

<h3>Installing Node</h3>
<p>See the instructions <a href="">here</a> for setting up Nodjs and getting a repl</p>


