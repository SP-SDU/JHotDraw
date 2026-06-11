# Lab Coverage Matrix

## Summary

All explicit lab exercises from `.agents/Labs.pdf` are covered for selected JHotDraw portfolio feature: `Snap-to-grid / constrained movement`.

The portfolio uses one coherent case study through setup, change request, concept location, impact analysis, refactoring, actualization, testing, CI, and BDD.

## Coverage

| Course lab | Main requirement | Portfolio coverage | Status |
| --- | --- | --- | --- |
| `IntroLab` | Fork/branch workflow, Maven setup, baseline build, launch JHotDraw GUI. | `portfolio.md` section 1, `evidence/test-results.txt`. | Met |
| `ChangeReqLab` | Select existing JHotDraw feature and write user story. | `portfolio.md` section 2. | Met |
| `CILab` | Add Maven GitHub Actions workflow and run tests automatically. | `.github/workflows/maven.yml`, `portfolio.md` section 3, `evidence/ci-workflow.yml`. | Met locally, remote run pending after PR |
| `AnalysisLab` | Estimate impact set and list packages/classes visited. | `portfolio.md` section 6, `evidence/impact-analysis-table.md`. | Met |
| `CLLab` | Locate feature concepts and list initial domain classes. | `portfolio.md` section 4, `evidence/concept-location-table.md`. | Met with static/runtime trace |
| `RefactoringLab` | Identify smell and apply behavior-preserving refactoring. | `portfolio.md` section 7, commit `4ab72d4b`. | Met |
| `ActualizationLab` | Explain implementation, propagation, SOLID, Clean Architecture. | `portfolio.md` section 8. | Met |
| `TestLab1` | Add automated tests for feature business logic. | `GridConstrainerTest`, `portfolio.md` section 9, `evidence/test-results.txt`. | Met |
| `BDDLab` | Map user story to Given-When-Then and automate with JGiven. | `GridConstrainerTest`, `portfolio.md` section 10, `evidence/bdd-scenarios.md`. | Met |

## Known Limits

| Limit | Reason | Mitigation |
| --- | --- | --- |
| JDK is Java 23 locally, not JDK11. | Available local environment uses Temurin 23. | CI workflow configures JDK11. Local mismatch documented. |
| Remote GitHub Actions URL absent. | User requested no push during work. Branch exists but no run is listed. | `gh run list --branch feature/snap-to-grid-portfolio` returned no workflow runs. |
| No IDE debugger screenshot. | Work was performed through code search and local runtime launch in this environment. | Concept classes and call tree are documented with exact paths and runtime command evidence. |

## Final Assessment

Lab requirements are met for a coherent portfolio. Remaining gaps are external evidence gaps, not missing implementation, testing, or documentation sections.
