# Evidence Index: Snap-to-grid / constrained movement

## Repository Evidence

| Item | Value |
| --- | --- |
| Repository | `https://github.com/SP-SDU/JHotDraw.git` |
| Branch | `feature/snap-to-grid-portfolio` |
| Branch URL | `https://github.com/SP-SDU/JHotDraw/tree/feature/snap-to-grid-portfolio` |
| CI workflow | `.github/workflows/maven.yml` |
| PR | No PR found by `gh pr list --head feature/snap-to-grid-portfolio --state all --limit 5`. |
| Workflow run | No run found by `gh run list --branch feature/snap-to-grid-portfolio --limit 5`. |

## Commit Evidence

| Commit | Purpose |
| --- | --- |
| `7c7a2630` | Add Maven CI workflow. |
| `4ab72d4b` | Refactor `GridConstrainer` and add BDD tests. |
| `a752a75c` | Add portfolio evidence. |
| `f6d49f67` | Generate portfolio PDF. |

## Portfolio Artifacts

| File | Purpose |
| --- | --- |
| `portfolio.md` | Main editable Markdown portfolio. |
| `portfolio.pdf` | Generated PDF portfolio with rendered tables and diagrams. |
| `labs/00_lab_coverage_matrix.md` | Lab-by-lab completion matrix. |
| `evidence/evidence_index.md` | This index. |

## Code Evidence

| File | Purpose |
| --- | --- |
| `jhotdraw-core/src/main/java/org/jhotdraw/draw/GridConstrainer.java` | Production class for snap-to-grid behavior. |
| `jhotdraw-core/src/test/java/org/jhotdraw/draw/GridConstrainerTest.java` | JUnit 4/JGiven/AssertJ automated tests. |
| `jhotdraw-core/pom.xml` | Test dependencies for JUnit 4, JGiven, and AssertJ. |
| `pom.xml` | Surefire module-opening configuration needed for JGiven under Java 23. |

## Analysis Evidence

| File | Purpose |
| --- | --- |
| `evidence/concept-location-table.md` | Initial concept classes and responsibilities. |
| `evidence/impact-analysis-table.md` | Estimated impact set and package visit table. |
| `evidence/call-tree.md` | Feature call tree and important calls. |
| `evidence/bdd-scenarios.md` | Given-When-Then scenarios. |
| `evidence/test-results.txt` | Local command outputs and build/test summaries. |
| `evidence/ci-workflow.yml` | Copy of CI workflow for portfolio appendix. |

## Verification Evidence

| Command | Result |
| --- | --- |
| `mvn clean install -DskipTests` | `BUILD SUCCESS` |
| `mvn -pl jhotdraw-core test -Dtest=GridConstrainerTest` | `5` tests, `0` failures, `BUILD SUCCESS` |
| `mvn test` | Reactor `BUILD SUCCESS` |
| `mvn exec:java "-Dexec.mainClass=org.jhotdraw.samples.svg.Main"` | SVG app started and stayed open until timeout, expected for Swing GUI. |
