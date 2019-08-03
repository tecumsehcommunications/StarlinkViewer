<h1>Blender Integration</h1>

<p>If you have background in 3D animation, skip ahead to the setup steps.  If not, let me explain a little.</p>

<p>Blender has a back door you can enter with the Python programming language.  When you do, you'll discover that all Blender data is organized into Python data structures.  So if you complete the setup described here, you will be able to load a Blender file in a Hy repl and run with it.  Let me give an example of how this is useful:<p/>
  
<p>To create the celestial coordinate system needed to accurately animate Starlink satellite orbits around the earth, I built the needed 3D geometries in Blender. Then I loaded that Blender scene into a Hy repl where I used Python's csv library to parse a star catalog and then create and plot stars in the Blender scene at their precise coordinates around the celestial globe I first created.  You can view how little code that took <a href="https://github.com/tecumsehcommunications/StarlinkViewer/blob/master/desktop/dev/plotStars">here</a>.  The before and after look of the Blender scene was this:</p> 

<p>Before</p>
<img src="img/before.png" style="width:80%"></img>
<p>After</p>
<img src="img/after.png" style="width:80%"></img>

<p>In order to do this kind of work, you need two versions of Blender running together.  One is the standard GUI version for working with Blender in that way and the other is Blender essentially running headless inside a Hy repl</p>

<p>After you complete this setup, you will be able to work on the Starlink Viewer applicataion as both a Hy program and a Blender Scene.  When you want to switch between the two perspectives, just save your work and reload the file in the environment you are switching to.  Yes, it is a two step process, and I am sure it could be improved--maybe using XDOTOOL?--but this is a trivial thing when you consider how much you gain by being able to build the application this way.</p>

<p>When we've leveraged Blender and Python all we can, we'll push our work up the toolchain by exporting the Blender scene to a format ThreeJS can pickup and load into a web browser.  Then we'll switch from Python/Hy to Javascript/Clojure and finish the job.  As Hy and Clojure are both lisp dialects, our transition between the two environments will hardly miss a beat.</p>

<h2>The Setup</h2>

<p>We are going to follow the procedure described
<a href="https://wiki.blender.org/wiki/Building_Blender/Linux/Ubuntu">here</a> fairly closely.  It is probably a good idea to keep that page open in another window as you work through these steps.  And be advised: this procedure can take hours, even days to finish. </p>

<p>Follow step one on that above-referenced page, just as given.  When you get to step two (running the install_deps.sh script), just make sure that you run the script with the --with-all option<p>

<p>When the install_deps script completes, it will write a BUILD_NOTES.txt file for you in the directory you ran it from.  When you look in that file, you will see a section that starts with something like: "If you are using cmake, add this to your configuration flags..."  copy all the -D flags that follow into a seperate buffer and format the text so you can copy and paste it onto the command line--I just add a "\" to the end of each line for this. </p> 

<h3>Ok, let's build the blender GUI first. </h3>     

<p>You can follow build instruction 3) on the blender website: (3 Compile Blender with CMake) but I have had more success with just doing the following: </p>

<p>1) In the top level of your working directory, the one named "blender-git" in blender's instructions, make a directory to hold your build.  I called mine linux_build.  Now cd into that directory and at the command prompt type:</p>

<pre><code>cmake ../blender</code></pre> 

<p>Don't press enter yet.  First copy all the cmake flags you readied previously and paste them at the end of the command. You also will want to add a -D CMAKE_INSTALL_PREFIX="path to installation folder of your choice" flag.  Now hit enter.  </p>

<p> 2) After cmake does its thing.  You do just as the instructions say.  You enter the directory from which you ran your cmake command and type "make" then "make install"</p>

<p>3) Now you should be able to go to the directory where you installed your newly built blender and inside the bin sub-directory you should see a blender executable to run to bring up the blender GUI.  At this pointer, I usually place a symbolic link to that executable in my $HOME/bin directory</p>

<h3>Building Blender as  Python Module</h3>
  

<p>For reference, you can refer to an archived website <a href="http://archive.is/hxSZ6">here,</a> but if you have already followed these instructions to build the blender GUI then you are nearly ready now.</p>

<p>Just follow the above instructions for building the GUI, as given, except make a bpy_build directory instead of a linux_build directory and edit the cmake parameters you used for the GUI build as follows: </p>

<p>1) Add -D WITH_PYTHON_INSTALL=OFF</p>
<p>2) Add -D WITH_PLAYER=OFF</p>
<p>3) Add -D WITH_PYTHON_MODULE=ON</p>
<p>4) Add -D WITH_INSTALL_PORTABLE=ON</p>
<p>5) Change -D CMAKE_INSTALL_PREFIX parameter to point to your python local modules directory.  On my system that is $HOME/.local/lib/python3.7/site-packages so the parameter is -D CMAKE_INSTALL_PREFIX=$HOME/.local/lib/python3.7/site-packages</p>

<P>6) Lastly, check that any of these options we just added is not cancelled out by any of the options we copied out of the BUILD_NOTES.txt file.  Once you've made that check, just run cmake, then make like you did to build the GUI and when it is done you should have a bpy.so and supporting files all installed where they need to be in your local python site-packages directory. </p>

<p>The test is simple.  Start a Hy repl and import the bpy module (import bpy).  If that succeeds, you are in business.</p>

