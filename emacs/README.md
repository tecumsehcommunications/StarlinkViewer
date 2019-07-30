<h1>EMACS AT THE EXCHANGE</h1>
<p>The lingua franca of this project is Lisp. Adopting Lisp means we can switch from working on one part of the tool chain to another without having to switch syntax. That makes the parts of the project more cohesive. Emacs, built with Lisp, has been around so long it is nearly organic to Lisp itself.  Thanks to Richard Stallman and the Gnu Project./p> 

<p>With Emacs, we can open a window on any part of the toolchain and work there.  It is the glue.  Let me suggest a few lines for emac's init.el file to help with that.  </p>

I use clojure-mode for working with lisp in Emacs, once that is installed, add: 

<pre><code>(add-to-list 'auto-mode-alist '("\\.hy\\'" . clojure-mode))</code></pre>

to the init.el file so Hy lisp buffers also use clojure-mode.

<p><a href="eLisp-snippet.el">Look</a> at the eLisp-snippet file in this folder. The idea there is pretty straight forward: You "C-u M-x: shell" in Emacs and give the shell buffer you are creating a name you have pre-defined in Emacs as a buffer to which you will be sending Lisp commands.  So for example, in the snippet I refer to, there is a line that says:

  <pre><code>(define-key clojure-mode-map (kbd "C-w")
      (lambda () (interactive) (repl-eval-last-expression "brepl")))</pre></code>
  
<p>What that means is if you follow the instructions for configuring Nginx found <a href="https://github.com/tecumsehcommunications/StarlinkViewer/tree/master/nginx">here</a>.  Then follow the directions for setting up a browser repl <a href="https://github.com/tecumsehcommunications/StarlinkViewer/tree/master/repls#the-client-browser-repl">here</a>.  Then you can type "C-u M-x: shell"  in Emacs, name the buffer "brepl" and in it cd to the /client directory of your clone of this project.  When you type:</p>
<pre><code>$ dev/brepl</code></pre>
<p>and then load a browser on the same machine and point it to http://localhost.  You should see a repl prompt appear in your brepl window.  Now, all you have to do is open a .cljs file in the client/src directory and you can start talking in real time to the client app running in the browser just by placing your cursor at the end of a lisp expression and doing a "C-w" in Emacs.  I hope that is clear.  For using Emacs to run other development repls, see here.  
  
  
  
  find yourself talking lisp to an application to the application running in the browser.  All you have to do is open or create a .cljs file in Emacs, type a lisp command in the buffer, position the cursor at the end of the command and type "C-w" in Emacs to send it to  expression, and   :and then start a browser-connected repl like the Clojure development tools provide (I provide repl startup scripts here), when you open a .cljs file into another emacs buffer, you can send clojurescript lisp commands from that buffer to a repl running in your browser just by doing a "C-w" keystroke. </p>

<p>This basic ability multiplies in utility as development becomes more complex.  For example, consider the following screenshot.</p>

<img src="img/devDesktop.png" style="width:80%"></img>

<p>From window A, we can direct commands into any of three available repl environements (B, C, D) which could simultaneously be running a node.js server and liveReload process, linked to a running browser, or attached to a blender/python dataset.  As you learn to work with more tools on the chain, this flexibility becomes very useful.</p>

<p>So also add the eLips-snippet.el file in this folder to your emacs init.el file and visit the repl folder for recommended repl startup scripts.</p>



