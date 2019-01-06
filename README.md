# Pencil kata

Implementation of [Pluralsight Pencil Kata](https://github.com/PillarTechnology/kata-pencil-durability)

Built on top of Gradle. The gradle configuration isn't the best, but the basic build and test cycle
should work
```
gradle test
```

## Developer Notes
Pushes on 01/05/19:

### Push One:
  * Lacking a pair partner, decided to brainstorm and track higher level design thoughts here.
  * Pushed previous progress (basic pencil writing) to GitHub
  * Seeing the potential number of constructors increasing,with potential confusion
  in order of several int parameters, branched to try a fluent interface before adding an eraser
  
### Push Two:
  * Extract guard conditions to simpler logical methods
  * Handling of eraser use cases
  * Git repository
    * Pushed feature branch to preserve history of building functionality
    * Did a PR that squash merged feature branch to master
    
    
### Push Three:
  * With a fresh mind, reviewed existing work and realized StringBuilder was being 
  used inefficiently. Reverted to the replace() method (can't recall why we had not stuck 
  with it)

### Push Four:
  * Adding editing function, decided that the reasonable use case is to consider only the case 
  of one erasure followed by one insertion (possible future  exercise, but complicates 
  interface for this exercise).
    * Logically, in editing, we do not choose any random blank area in which to edit, and we erase, 
    then write, in one logical sequence. Collapsed to one method