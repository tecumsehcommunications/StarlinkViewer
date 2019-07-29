<h1>GET YOUR REPLs READY</h1>
<p>The ability to interact with a program as you are building it really helps.  That is what motivates the use of repls 
throughout this project</p>

<p>We have two target languages which are applied to our toolchain in three parts:  Python (via Hy) which backends the Desktop development
environment, and Javascript which is the target language of both the server (nodejs) and client (browser) parts of the chain. </p>

<h2>The Hy Repl</h2>
<p>Setting up a Hy repl on the Ubuntu server I've described is fairly painless.  By default, it should come with both 
python2.7 and python3.7 pre-installed.  The one thing I have had to watch out for is which python (2.7 or 2.3) runs when you type "python"
at the command prompt.  You want it to be python 3, not python 2.  I just put a symbolic link named "python" in my /bin directory and point 
to the python3 executable at /usr/bin/python3.  That seems to do the trick.

After you know python3 is the default python for you, just type: 
<pre><code>$ pip3 install --user git+https://github.com/hylang/hy.git </code></pre>

That should be it.  From now on, if you want to run a hy repl in an emacs shell, just type "C-u M-x shell" in Emacs and name the buffer "hrepl."
Thentype "hy" at the command prompt.</p> That's it.

<h2>The Server (Nodejs) Repl</h2>

<p>To setup a repl for working with Nodejs, we first install Clojure.  The Clojure people give you a one-two-buckle-your-shoe procedure 
for this <a href="https://clojure.org/guides/getting_started#_installation_on_linux">here</a>.  Thanks to Rich 
Hickey and everyone in that development community.  </p>

<p>Once Clojure is installed, you need to install Nodejs.  That should be fairly painless as well. Just download the binaries you need from 
<a href="https://nodejs.org/en/download/">here</a>, unzip them into a directory of your choosing, and make sure the /bin subdirectory 
of that directory is on your $PATH. </p>

<p>With Nodejs and Clojure installed, your next step is to clone this project and then type "C-u M-x shell" in Emacs and name the buffer 
"nrepl."  From within that buffer, cd to the /server directory of this project and type dev/nrepl.  Now you can talk lisp to Nodejs.</p>

<h2>The Client (Browser) Repl</h2>

<p>The client repl requires more setup than the server repl because it will load and run on a web page in a browser window.  This is where the
Nginx part of our toolchain comes into play.  If you have not already, you should go through the steps of setting Nginx up on your machine
described <a href="https://github.com/tecumsehcommunications/StarlinkViewer/tree/master/nginx">here</a>.  It should only take you a few minutes.

fairly quick and painless process. </p>
your are ready to run Clojurescript repls in both nodejs and browser environments.  I've already provided 
the remaining configuration files needed along with this project's source code.  Just clone this project, then type "C-u M-x shell" in emacs, name the buffer "brepl" 
and cd into this project's "client" directory.  Then type "dev/brepl" for a browser repl. But remember that to use the browser repl you 
also will need the corresponding web page loaded into a browser on your machine.   Look <a href="https://github.com/tecumsehcommunications/StarlinkViewer/tree/master/nginx">
here</a> to verify you have that part of the chain in place.   

Starting a node repl is similarly simple.  type  "C-u M-x shell" in Emacs and name the buffer nrepl.  Clone this project and cd to the "server" 
directory.  Then type dev/nrepl at the command prompt.  

Before you continue, you should review the directory structure assumptions we work with here.
