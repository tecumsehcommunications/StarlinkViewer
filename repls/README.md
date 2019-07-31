<h1>GET YOUR REPLs READY</h1>
<p>The ability to interact with a program as you are building it really helps.  That is what motivates the use of repls 
throughout this project</p>

<p>We have two target languages which are applied to our toolchain in three parts:  Python (via Hy) which backends the Blender/Desktop environment, and Javascript (via Clojurescript) which is the target language of both the Server (nodejs) and Client (browser) parts of the chain. </p>

<h2>The Hy Repl</h2>
<p>Setting up a Hy repl on the Ubuntu server I've described is fairly painless.  By default, the server should come with both python2.7 and python3.7 installed.  The one thing I have had to watch out for is which Python (2.7 or 3.7) runs when you type "python"
at the command prompt.  You want it to be python 3, not python 2.  I just put a symbolic link named "python" in my /bin directory and point to the python3 executable at /usr/bin.  That seems to do the trick.</p>

<p>After you know python3 is the default python for you, just type: </p> 
<pre><code>$ pip3 install --user git+https://github.com/hylang/hy.git </code></pre>

<p>That should be it.  To test it, if you have not already, clone this project into a local folder and open the scratch.hy file found in the /desktop/dev directory into an Emacs buffer. Create a second Emacs window or frame and then type "C-u M-x shell" in Emacs and name the buffer "hrepl." Then type "hy" at that shell's command prompt. Jump back to the buffer you opened for the scratch.hy file and place the cursor just to the right of the closing paren ")" of the (print "Hello World") expression and type "C-e" in Emacs. You hould see the results in the hrepl buffer.  That's it.</p>

<h2>The Server (Nodejs) Repl</h2>

<p>To setup a repl for working with Nodejs, we first install Clojure.  The Clojure people give you a one-two-buckle-your-shoe procedure 
for this <a href="https://clojure.org/guides/getting_started#_installation_on_linux">here</a>.  Thanks to Rich Hickey and everyone in that development community.  </p>

<p>Once Clojure is installed, you need to install Nodejs.  That should be fairly painless as well. Just download the binaries you need from <a href="https://nodejs.org/en/download/">here</a>, unzip them into a directory of your choosing, and make sure the /bin subdirectory of that directory is on your $PATH. </p>

<p>With Nodejs and Clojure installed, your next step is to clone this project and then type "C-u M-x shell" in Emacs and name the buffer 
"nrepl."  From within that buffer, cd to the /server directory of this project and type dev/nrepl at the nrepl shell's command prompt.  To test the repl, open the /server/dev/scratch.cljs file in another emacs buffer, place the cursor to the left of any closing paren ")" and type "C-q in Emacs.  The results should show in the nrepl buffer.</p>

<h2>The Client (Browser) Repl</h2>

<p>The client repl requires more setup than the server repl because it will be interacting with a web page loaded into browser window.  This is where the Nginx part of our toolchain comes into play.  If you have not already, you should go through the steps of setting Nginx up on your machine described <a href="https://github.com/tecumsehcommunications/StarlinkViewer/tree/master/nginx">here</a>.  It should only take you a few minutes.</p>

Once you've got Nginx integrated into the project, assuming you already have cloned this project to a local folder, you first need to load the client app into a browser in order to talk to it.  Just open a browser on your machine and point it to <a href="http://localhost">http://localhost</a>.  Then type "C-u M-x shell" in Emacs and name the buffer "brepl."  Now open a second window/frame in Emacs and load the scratch.cljs file found in the client/dev sub directory of the project.  Place your cursor at the end of the (print "Hello World!") expression in the buffer and type "C-w" in Emacs.  The results should show in the brepl buffer.  You may have to reload the browser page in order to get it to connect to your emacs repl the first time.</p> 
