# Pencil kata

Implementation of [Pluralsight Pencil Kata](https://github.com/PillarTechnology/kata-pencil-durability)
Modeling a Pencil with a set of desired attributes; an unspecified Writer and a concept of Paper 
are implicitly assumed but not modeled.

## testing the kata
* clone this repository
* The project is built on top of Gradle. The gradle configuration isn't the best, but the 
basic build and test cycle should work. To test:

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
    

Pushes on 01/06/2019:
  
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
    
## Reflections on work

  * Early on, decided to maintain attitude of restraining early optimizations or over-design.
    * This is my known weak point, and drilling myself out of that bad habit is the best use of 
    my time. Usually it's pairing that curbs this tendency; not leaning on that was a 
    good mental exercise
    * After review of the total set of goals, did linear development of features, which 
    surprisingly to me did not result in very many times where code needed to be 
    entirely redesigned or rewritten.
      * I'm not sure how to interpret the "hint" on how to pick ordering of test cases; I would  
      be eager to hear more on this insight.
  * Solution is not overly OO -- there is no Paper class, for instance. However, use cases did 
  not provide a justification, as the only collaborators are the (unseen) Writer and the Pencil.
  The text is the side-effect of each use of the Pencil, and that's what we do. Very YAGNI, and 
  this exercise somewhat strains the OO paradigm in any case. Might be fun to rewrite 
  in Clojure since I want to learn it.
  * Solution could be more optimized -- we operated character by character, which makes a 
  System.arraycopy call for each character.
    * First step in this would be a functional test once we can define something like 
    scribing the First Folio, which is out of scope in a kata.
   
  * _This was fun_. I'll do all the other exercises in the future, and scan Uncle Bob 
  for more ideas.
    