package org.jhotdraw.draw;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.junit.ScenarioTest;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GridConstrainerTest extends ScenarioTest<GridConstrainerTest.GivenGrid, GridConstrainerTest.WhenGrid, GridConstrainerTest.ThenGrid> {

    @Test
    public void givenPointNearGrid_whenConstrainPoint_thenSnapsToNearestGrid() {
        given().a_grid_constrainer(10, 5)
                .and().a_point(14, 13);

        when().the_point_is_constrained();

        then().the_point_is(10, 15);
    }

    @Test
    public void givenRectangleNearGrid_whenConstrainRectangle_thenMovesNearestEdgeToGrid() {
        given().a_grid_constrainer(10, 10)
                .and().a_rectangle(13, 26, 12, 8);

        when().the_rectangle_is_constrained();

        then().the_rectangle_is(10, 22, 12, 8);
    }

    @Test
    public void givenDirectionEast_whenTranslatePoint_thenMovesOneGridCellEast() {
        given().a_grid_constrainer(10, 10)
                .and().a_point(13, 26);

        when().the_point_is_translated(TranslationDirection.EAST);

        then().the_point_is(20, 26);
    }

    @Test
    public void givenTheta_whenConstrainAngle_thenRoundsToNearestStep() {
        given().a_grid_constrainer(10, 10, Math.PI / 4);

        when().the_angle_is_constrained(0.70d);

        then().the_angle_is(Math.PI / 4);
    }

    @Test
    public void givenNullRotationDirection_whenRotateAngle_thenThrowsIllegalArgumentException() {
        given().a_grid_constrainer(10, 10, Math.PI / 4);

        when().the_angle_is_rotated_with_failure(null);

        then().the_failure_is_an_illegal_argument_exception();
    }

    public static class GivenGrid extends Stage<GivenGrid> {

        @ProvidedScenarioState
        GridConstrainer constrainer;
        @ProvidedScenarioState
        Point2D.Double point;
        @ProvidedScenarioState
        Rectangle2D.Double rectangle;

        public GivenGrid a_grid_constrainer(double width, double height) {
            constrainer = new GridConstrainer(width, height);
            return self();
        }

        public GivenGrid a_grid_constrainer(double width, double height, double theta) {
            constrainer = new GridConstrainer(width, height, theta, true);
            return self();
        }

        public GivenGrid a_point(double x, double y) {
            point = new Point2D.Double(x, y);
            return self();
        }

        public GivenGrid a_rectangle(double x, double y, double width, double height) {
            rectangle = new Rectangle2D.Double(x, y, width, height);
            return self();
        }
    }

    public static class WhenGrid extends Stage<WhenGrid> {

        @ExpectedScenarioState
        GridConstrainer constrainer;
        @ExpectedScenarioState
        Point2D.Double point;
        @ExpectedScenarioState
        Rectangle2D.Double rectangle;
        @ProvidedScenarioState
        double angle;
        @ProvidedScenarioState
        Throwable failure;

        public WhenGrid the_point_is_constrained() {
            constrainer.constrainPoint(point);
            return self();
        }

        public WhenGrid the_rectangle_is_constrained() {
            constrainer.constrainRectangle(rectangle);
            return self();
        }

        public WhenGrid the_point_is_translated(TranslationDirection direction) {
            constrainer.translatePoint(point, direction);
            return self();
        }

        public WhenGrid the_angle_is_constrained(double input) {
            angle = constrainer.constrainAngle(input);
            return self();
        }

        public WhenGrid the_angle_is_rotated_with_failure(RotationDirection direction) {
            try {
                angle = constrainer.rotateAngle(0, direction);
            } catch (Throwable t) {
                failure = t;
            }
            return self();
        }
    }

    public static class ThenGrid extends Stage<ThenGrid> {

        @ExpectedScenarioState
        Point2D.Double point;
        @ExpectedScenarioState
        Rectangle2D.Double rectangle;
        @ExpectedScenarioState
        double angle;
        @ExpectedScenarioState
        Throwable failure;

        public ThenGrid the_point_is(double x, double y) {
            assertThat(point.x).isEqualTo(x);
            assertThat(point.y).isEqualTo(y);
            return self();
        }

        public ThenGrid the_rectangle_is(double x, double y, double width, double height) {
            assertThat(rectangle.x).isEqualTo(x);
            assertThat(rectangle.y).isEqualTo(y);
            assertThat(rectangle.width).isEqualTo(width);
            assertThat(rectangle.height).isEqualTo(height);
            return self();
        }

        public ThenGrid the_angle_is(double expected) {
            assertThat(angle).isEqualTo(expected);
            return self();
        }

        public ThenGrid the_failure_is_an_illegal_argument_exception() {
            assertThat(failure).isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("dir must not be null");
            return self();
        }
    }
}
