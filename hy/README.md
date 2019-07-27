<h1>Ready Hy and Python</h1>

<p>The Ubuntu I am running right now is 19.04.  It ships with python 2.7 and 3.7.  Natively, when you type "python" on the command line, 
you get a Python 2.7 repl.  Make it so "python" instead invokes a Python3 repl--however you see fit.  I just add a symbolic link named python to the ~/bin directory and point it to 
the python3 executable. 

<p>Also make sure pip3 is installed.</p>

<p>When python is ready,  at the command prompt I type:</p> 

<pre><code>$ pip3 install --user git+https://github.com/hylang/hy.git </code></pre>

<p>Now, when you type "hy" on the command line, it should return a hy repl to you.  

<p>You also should look at the README.md file in the emacs folder of this project if you have not already.</p>

