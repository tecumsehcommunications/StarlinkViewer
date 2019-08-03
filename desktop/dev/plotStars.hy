(import csv)
(import bpy)
(import math)

; open a blender file
(bpy.ops.wm.open_mainfile
 :filepath "dev/celestial-globe.blend") 
; when all is done, save the results
(bpy.ops.wm.save_mainfile
 :filepath "dev/celestial-globe+stars.blend") ; run this to save results to new file

; get the proto-star already made in blender, etc.
(setv starProto (. bpy.data.objects ["star-proto"]))
(setv scene bpy.context.scene)
(setv radius 6)

;function receives a copy of the star-poro object and plots the object onto the surface of a sphere of a given radius. The following <a href="https://www.amsat.org/keplerian-elements-tutorial/">website</a> has a good tutorial on the Keplerian Elements.  This will come in handy later when we start plotting satellite orbits.

(defn plotStar [ star asca deca ]
  (setv ascr (math.radians (* 15 asca)))
  (setv dec-compr (math.radians
                  (- 90 (math.fabs deca))))
  (setv star.location.x (* radius
                          (math.cos ascr)
                          (math.sin dec-compr)))
  (setv star.location.y (* radius
                           (math.sin ascr)
                           (math.sin dec-compr)))
  (setv star.location.z
        (if (< deca 0)
          (* radius (-(math.cos dec-compr)))
          (* radius (math.cos dec-compr)))))

; a little factory to read/parse the data then setup a new star in the scene with plotStar
(with [ csv_file (open "dev/brightest1024.csv") ]
      (setv csv_reader
            (csv.reader csv_file :delimiter ","))
      (for [ row csv_reader ]
        (setv catnum (. row [0]))
        (setv asca (float (. row [7])))
        (setv deca (float (. row [8])))
        (setv newstar (starProto.copy))
        (setv newstar.data (starProto.data.copy))
        (newstar.animation_data_clear)
        (scene.collection.objects.link newstar)
        (plotStar newstar asca deca)))
