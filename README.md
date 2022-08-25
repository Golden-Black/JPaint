# JPaint
#### Link to Repo: https://github.com/Golden-Black/JPaint.git 

### Sprint 1:
#### -- Bugs Encountered: 
1. (<b>SOLVED</b>) Width not properly shown. Error fixed by updating line 38 in ClickHandler.java from  "<b>commit dbd9c25</b>" (deprecated). 
#### -- Extra Credit: N/A
#### -- Missing Features: - 
#### -- Miscellaneous: 
1. The project used OpenJDK 18;


### Sprint 2:
#### -- Bugs Encountered:
1. (<b>SOLVED</b>) Triangle doesn't draw using fillPoly or draw Poly.
   Error fixed by creating and passing the vertices arrays in ClickHandler.java instead of creating & using them in DrawTriangle.java from "commit 658cb2e" (deprecated).
#### -- Extra Credit: N/A
#### -- Missing Features: -
1. Select a shape (Solved)
2. Move a shape

#### -- Miscellaneous:
1. Group members' individual branches were created;

### Sprint 3:
#### -- Bugs Encountered:
1. Click Shape is buggy and it will sometimes select the shape units
#### -- Extra Credit: N/A
#### -- Missing Features: -
1. Delete: Decided to use another command pattern on Delete but ended up running out of time
2. Undo/Redo
#### -- Miscellaneous:
1. Transitioned back to main branch from individual branch (ziyan-new-branch) following the announcement "Project Check-ins 3 and 4" posted on July 28th in D2L;
2. Didn't feel good last week and the overall progress slowed down because of that.

### Sprint 4:
#### -- Bugs Encountered:
1. Outline doesn't go away when select new shapes
#### -- Extra Credit: N/A
#### -- Missing Features: -
1. Undo/Redo
#### -- Miscellaneous:

### Final Delivery:
#### -- Bugs Encountered:
1. Not sure why but undo/redo needs double click to work.
#### -- Extra Credit: N/A
#### -- Missing Features: 
1. Group: Once shapes are grouped, the outline will stick will the shapes instead of forming a square that contains the shapes.
#### -- Miscellaneous:
1. Delete Reconfigured - Now Fully Functional
2. Colors for Move & Delete Added
3. Copy & Paste Updated - Now Fully Functional
#### -- Design Patterns
1. Command Pattern 
   1. Classes: ClickHandler, CreateShapeCommand, MoveShapeCommand, SelectShapeCommand
2. Abstract Factory Pattern
   1. Classes: CreateEclipse, CreateRectangle, CreateTriangle
3. Observer Pattern
   1. Classes: ShapeList, CopyShapeList, deleteShapeList, pasteShapeList
4. Composite Pattern
   1. Classes: ShapeList, CreateShapeCommand, MoveShapeCommand, SelectShapeCommand
5. Strategy Pattern
   1. Classes: CreateEclipse, CreateRectangle, CreateTriangle