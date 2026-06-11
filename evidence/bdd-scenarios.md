# BDD Scenarios - Snap-to-grid / constrained movement

User story: As a drawing user, I want snap-to-grid and constrained movement, so that I can place figures with consistent alignment.

```gherkin
Scenario: Point snaps to nearest grid intersection
Given a grid constrainer with width 10 and height 5
And a point at 14, 13
When the point is constrained
Then the point is moved to 10, 15
```

```gherkin
Scenario: Rectangle moves nearest edge to grid
Given a grid constrainer with width 10 and height 10
And a rectangle at 13, 26 with size 12 by 8
When the rectangle is constrained
Then the rectangle is moved to 10, 22 without changing size
```

```gherkin
Scenario: Selected object moves east by one constrained grid cell
Given a grid constrainer with width 10 and height 10
And a point at 13, 26
When the point is translated east
Then the x coordinate becomes 20
And the y coordinate remains 26
```

Automation file: `jhotdraw-core/src/test/java/org/jhotdraw/draw/GridConstrainerTest.java`.
