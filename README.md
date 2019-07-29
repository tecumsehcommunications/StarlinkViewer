<html>
<body>
  
<h1> Chapter I:  The Challenge </h1>

<p> The idea is simple.  We build an app that makes it so anyone who wants can point a computer camera at the sky and see a superimposed time-lapse animation of Starlink satellites that have been and will be passing overhead...with precision. &nbsp Sound good? </p>

<p>For obvious reasons, this has to run in a web browser.  So the app we are building essentially will be a 3d interactive animation using the javascript webGL api.  The tool chain for this kind of work is as follows: </p>
<p></p>
  
<img src="img/workflow.svg" style="margin-top:100px; width:80%;"></img>

<p></p>

<p>I believe this currently is the most powerful 3d publishing tool chain on the planet, and with it we are going to make a world-class application available world-wide for free.</p>

<p>So saddle up, nerds.  It's time to ride.</p>

<h1>Chapter 2: The Bootstrap </h1>

<p>You don't have to work with the entire toolchain above to contribute.  Just understand how the chain works, see your place in it and keep track of where the project is headed so you know how to push your work along.  As you go, you'll probably find yourself working in different areas, so here I am going to install the whole chain with you and show you a little how its parts are meant to work together. 

<p>First you should have a current and up-to-date version of Ubuntu to work with.  Right now that is v19.04.  Configure it however suits you.  I just use the Server edition of the distro and add xorg, openbox, and emacs.  It boots to a console, then let's me log in and run startx for a windows environment.  This is a very capable machine.  You can develop, build, and test both the client and server sides of the app we are working on together, even at the same time. </p>

<p>After Ubuntu comes Emacs.  The distro version woks for me.  I recommend a very simple, low-drag setup for emacs that let's it be what it needs to be for the toolchain to work:  the central exchange. Please take a look at that <a href="https://github.com/tecumsehcommunications/StarlinkViewer/tree/master/emacs">here</a>.</p> 
  
  
<p>1) Get a current version of Ubuntu running (currently bundles Python 3.7) </p>

<p>2) Install Emacs, Hy, Nodejs, Nginx, and Cojure/ClojureScript</p>

<p>3) See the emacs, nodejs, and nginx directories of this project for configuration files and instructions</p>

<p>4) Compile blender as a python module (see the <a href="https://github.com/tecumsehcommunications/StarlinkViewer/tree/master/blender">blender</a> folder of this project for instructions)</p>

<p>5) Clone this project </p>

<p>6) Start hacking you magnificent bastard. </p>

<h1>Chapter 3: Onward </h1>

<p>The Starlink viewer is just a piece of a larger puzzle that needs to be assembled in order for the Starlink network to come online.  Tecumseh Communications was chartered to work on that whole puzzle and to facilitate across the spectrum of trades, disciplines, and skill-sets that will need to come together for all this to take place. </p>

<p>Our work product in those areas can be accessed via the other repositories we manage on this site.  Have a look here and see if you can use or contribute something. </p>

</body>
</html>
