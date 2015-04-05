# RoundMotionTest

Sample Android project, that shows how to implement animated views, that should moves by circle path around center view.

RotatingCustomView
This is the class, that allows you to add childs and central views to it and then rotate.
Inside startRotation() all the magic happens. 
Using ValueAnimator we move every view with setTranslationX and setTranslationY, according the angel, 
from what view should start to move.
