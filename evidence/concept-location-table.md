# Concept Location Table - Snap-to-grid / constrained movement

| Domain Class | Package | How Found | Responsibility |
| --- | --- | --- | --- |
| `GridConstrainer` | `org.jhotdraw.draw` | Search for `GridConstrainer`, runtime UI path, test target | Implements grid snapping for points, rectangles, translations, rotations, and grid drawing. |
| `Constrainer` | `org.jhotdraw.draw` | Call hierarchy from `DrawingView.getConstrainer` | Strategy interface for editing constraints used by tools and handles. |
| `DrawingView` | `org.jhotdraw.draw` | Runtime concept from selected view | Owns visible/invisible constrainers and exposes current constrainer. |
| `DefaultDrawingView` | `org.jhotdraw.draw` | Implementation of `DrawingView` | Returns visible or invisible constrainer based on grid visibility state. |
| `ToggleGridAction` | `org.jhotdraw.draw.action` | Search for grid toggle action | Toggles whether visible constrainer is active in current view. |
| `MoveConstrainedAction` | `org.jhotdraw.draw.action` | Search for constrained movement action | Moves selected transformable figures by one constrained unit. |
| `AbstractTool` | `org.jhotdraw.draw.tool` | Call hierarchy for `constrainPoint` | Converts view coordinates and delegates point snapping to current constrainer. |
| `ViewToolBar` | `org.jhotdraw.samples.svg.gui` | SVG GUI path from required run command | Shows grid toggle and grid size field for SVG sample. |
| `SVGApplicationModel` | `org.jhotdraw.samples.svg` | SVG application model path | Creates default `GridConstrainer(12, 12)` for SVG views. |

Method: static search plus runtime launch of SVG sample. Manual debugger evidence is not available in this environment.

TODO: Add IDE debugger screenshot or stack trace if required by instructor.
