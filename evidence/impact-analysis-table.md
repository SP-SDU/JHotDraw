# Impact Analysis Table - Snap-to-grid / constrained movement

| Class | Package | Mark | Reason |
| --- | --- | --- | --- |
| `GridConstrainer` | `org.jhotdraw.draw` | CHANGED | Contains snap coordinate, rectangle, translation, and angle logic. Refactoring target. |
| `Constrainer` | `org.jhotdraw.draw` | PROPAGATES | Interface defines public contract used by view, tools, and actions. No signature change. |
| `DrawingView` | `org.jhotdraw.draw` | PROPAGATES | View owns visible/invisible constrainers and exposes current constrainer. No change needed. |
| `DefaultDrawingView` | `org.jhotdraw.draw` | PROPAGATES | Runtime holder of current `GridConstrainer`. No change needed. |
| `AbstractTool` | `org.jhotdraw.draw.tool` | UNCHANGED | Calls `constrainPoint` during pointer operations. Behavior preserved. |
| `MoveConstrainedAction` | `org.jhotdraw.draw.action` | UNCHANGED | Calls `translateRectangle` for keyboard constrained movement. Behavior preserved. |
| `ToggleGridAction` | `org.jhotdraw.draw.action` | UNCHANGED | Toggles constrainer visibility. Not affected by coordinate refactor. |
| `ViewToolBar` | `org.jhotdraw.samples.svg.gui` | UNCHANGED | Displays grid size and toggle UI. Not affected by domain refactor. |
| `SVGApplicationModel` | `org.jhotdraw.samples.svg` | UNCHANGED | Creates default `GridConstrainer(12, 12)`. No constructor change. |
| `GridConstrainerTest` | `org.jhotdraw.draw` | CHANGED | New verification and BDD automation for selected feature. |

| Package name | # classes visited | Comments |
| --- | ---: | --- |
| `org.jhotdraw.draw` | 5 | Core domain contracts and implementation for constraining editing operations. |
| `org.jhotdraw.draw.action` | 2 | Swing action entry points for toggling grid and constrained keyboard movement. |
| `org.jhotdraw.draw.tool` | 1 | Mouse editing path that delegates snapping to current view constrainer. |
| `org.jhotdraw.samples.svg` | 1 | SVG sample creates default grid constrainer used by portfolio run command. |
| `org.jhotdraw.samples.svg.gui` | 1 | SVG toolbar exposes grid toggle and grid size control. |

Minimal impact set: production change limited to `GridConstrainer` and build/test metadata.
