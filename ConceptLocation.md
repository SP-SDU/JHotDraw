# Concept Location — Initial Set of Domain Classes

**Project:** JHotDraw  
**Analysis Type:** Dynamic Program Analysis (IDE Debugger)  
**Date:** March 9, 2026

## Overview

Dynamic program analysis was performed by running JHotDraw in the IDE debugger and tracing execution from the application entry point down through the drawing framework. Breakpoints were placed at key controller classes (`Application`, `DrawingEditor`) and interaction events (`Tool` mouse handlers, `Handle` track methods) to locate the domain classes involved in the drawing editor feature set.

## Domain Class Table

| Domain Class | Package | Responsibility |
|---|---|---|
| `Figure` *(interface)* | `org.jhotdraw.draw.figure` | Root contract for all graphical elements. Knows its bounds, can draw itself, holds open-ended attributes via `AttributeKey`, provides `Connector`s for connection points, and creates `Handle`s for direct manipulation. |
| `AbstractFigure` | `org.jhotdraw.draw.figure` | Abstract base implementation of `Figure`. Manages event listeners, the owning `Drawing` reference, and flags such as `isSelectable`, `isVisible`, and `isTransformable`. |
| `Drawing` *(interface)* | `org.jhotdraw.draw` | Container (itself a `CompositeFigure`) that holds `Figure` objects. Acts as mediator for undoable-edit events, delegates spatial queries, and holds `InputFormat`/`OutputFormat` strategies for I/O. |
| `QuadTreeDrawing` | `org.jhotdraw.draw` | Concrete `Drawing` backed by a `QuadTree<Figure>` to optimise spatial lookups (`findFigureInside`, `findFigures`) in drawings with many figures. |
| `DrawingView` *(interface)* | `org.jhotdraw.draw` | Renders a `Drawing` on a `JComponent`. Maintains the figure selection set, requests `Handle`s from selected figures, converts between view and drawing coordinates, and delegates positional constraints to a `Constrainer`. |
| `DefaultDrawingView` | `org.jhotdraw.draw` | Default `DrawingView` implementation. Manages `selectedFigures`, `selectionHandles`, double-buffered repaint, and wiring to the active `DrawingEditor`. |
| `DrawingEditor` *(interface)* | `org.jhotdraw.draw` | Mediator that coordinates `Tool`s and `DrawingView`s. Routes mouse and keyboard events, stores default figure attributes, and exposes `InputMap`/`ActionMap` for keyboard shortcuts. |
| `DefaultDrawingEditor` | `org.jhotdraw.draw` | Default `DrawingEditor`. Tracks the active `DrawingView`, the current `Tool`, and per-editor default/handle attribute maps. |
| `Tool` *(interface)* | `org.jhotdraw.draw.tool` | Defines an editing mode. Receives mouse and keyboard events from all `DrawingView`s of its editor and translates them into undoable operations on `Figure`s or the `Drawing`. |
| `SelectionTool` | `org.jhotdraw.draw.tool` | Composite tool that delegates to `DefaultSelectAreaTracker`, `DefaultDragTracker`, or `DefaultHandleTracker` depending on whether the user performs an area select, a figure drag, or handle manipulation. |
| `CreationTool` | `org.jhotdraw.draw.tool` | Creates new figures using the Prototype pattern: clones a prototype `Figure`, sets its bounds from a mouse press-drag-release gesture, then adds it to the drawing with an undoable edit. |
| `ConnectionTool` | `org.jhotdraw.draw.tool` | Creates a `ConnectionFigure` between two figures. Uses `Connector`s supplied by each figure to determine the exact start and end attachment points. |
| `Handle` *(interface)* | `org.jhotdraw.draw.handle` | Owned by a `Figure` and associated with a `DrawingView`. Renders a manipulation grip and responds to `trackStart`, `trackStep`, and `trackEnd` events from `DragTracker` to change one aspect of the owning figure. |
| `ResizeHandleKit` | `org.jhotdraw.draw.handle` | Factory that creates the eight directional resize handles for a figure's bounding rectangle (N, NE, E, SE, S, SW, W, NW). |
| `ConnectionFigure` *(interface)* | `org.jhotdraw.draw.figure` | Specialisation of `Figure` that connects two figures via a `BezierPath`. Start/end locations are resolved by `Connector`s; path layout can be delegated to a `Liner`. |
| `Connector` *(interface)* | `org.jhotdraw.draw.connector` | Strategy for locating the start or end connection point on a `Figure`. Concrete implementations (e.g. `ChopRectangleConnector`, `ChopEllipseConnector`) intersect the figure's geometric outline with the connection direction vector. |
| `AttributeKey<T>` | `org.jhotdraw.draw` | Typed key used to get/set open-ended style attributes (fill colour, stroke, opacity, font, etc.) on a `Figure` or a `Drawing` in a type-safe manner. |
| `AttributeKeys` | `org.jhotdraw.draw` | Registry of all well-known `AttributeKey` constants such as `FILL_COLOR`, `STROKE_COLOR`, `STROKE_WIDTH`, `CANVAS_WIDTH`, and `CANVAS_HEIGHT`. |
| `Application` *(interface)* | `org.jhotdraw.api.app` | Application-lifecycle controller. Creates and manages `View` instances, provides the application-level `ActionMap`, and owns the `ApplicationModel` which supplies metadata and factory methods. |
| `View` *(interface)* | `org.jhotdraw.api.app` | Represents an open document within an `Application`. Manages its full lifecycle (init → start → activate → deactivate → stop → dispose) and reads/writes the document identified by a URI. |
