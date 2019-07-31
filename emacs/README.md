<h1>EMACS AT THE EXCHANGE</h1>
<p>The lingua franca of this project is Lisp. Adopting Lisp means we can switch from working on one part of the tool chain to another without having to switch syntax. That makes the parts of the project more cohesive. Emacs, built with Lisp, has been around so long it is nearly organic to Lisp itself.  Thanks to Richard Stallman and the Gnu Project./p> 

<p>With Emacs, we can open a window on any part of the toolchain and work there.  It is the glue.  Let me suggest a few lines for emac's init.el file to help with that.  </p>

I use clojure-mode for working with lisp in Emacs, once that is installed, add: 

<pre><code>(add-to-list 'auto-mode-alist '("\\.hy\\'" . clojure-mode))</code></pre>

to the init.el file so Hy lisp buffers also use clojure-mode.

<p><a href="eLisp-snippet.el">Look</a> at the eLisp-snippet file in this folder. The idea there is pretty straight forward: You "C-u M-x: shell" in Emacs and give the shell buffer you are creating a name you have pre-defined in Emacs as a buffer to which you will be sending Lisp commands.  So for example, in the snippet I refer to, there is a line that says:

  <pre><code>(define-key clojure-mode-map (kbd "C-w")
      (lambda () (interactive) (repl-eval-last-expression "brepl")))</pre></code>
  
<p>What that means is, after you setup your repl environments <a href="https://github.com/tecumsehcommunications/StarlinkViewer/tree/master/repls">here</a>, and configured Nginx as decribed <a href="https://github.com/tecumsehcommunications/StarlinkViewer/tree/master/nginx">here</a> you will be able to talk to our client application running in a web browser just by entering "C-w" at the end of a lisp expression in Emacs. 
 
<p>This basic ability of Emacs to send commands between open windows running seperate repl environments multiplies in utility as development becomes more complex.  For example, consider the following screenshot.</p>

<img src="img/devDesktop.png" style="width:80%"></img>

<p>From window A, we can direct commands into any of three available repl environements (B, C, D) which could all be windowed into different parts of the workflow e.g., the Desktop, the Server, and the Client development environments. 
  
<h3>So also add the eLips-snippet.el file in this folder to your emacs init.el file and visit the repl setup page <a href="https://github.com/tecumsehcommunications/StarlinkViewer/tree/master/repls">here</a>. To get your repls going.</p>



