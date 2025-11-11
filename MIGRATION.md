# Migration Guide: Earthly/Jenkins → GitHub Actions/Dagger

This document outlines the migration from the previous Earthly/Jenkins CI/CD setup to the new GitHub Actions with Dagger approach.

## What Changed

### Before (Earthly/Jenkins)
- **CI Platform**: Jenkins with Kubernetes agents
- **Build Tool**: Earthly with containerized builds
- **Configuration**: `Jenkinsfile` + `Earthfile`
- **Artifacts**: `castle-casters.jar` archived in Jenkins

### After (GitHub Actions/Dagger)
- **CI Platform**: GitHub Actions with Ubuntu runners
- **Build Tool**: Dagger with TypeScript pipeline definitions
- **Configuration**: `.github/workflows/ci.yaml` + `.dagger/` module
- **Artifacts**: Uploaded to GitHub Actions artifacts

## Key Improvements

1. **Type Safety**: Pipeline logic is now written in TypeScript instead of shell scripts
2. **Better Error Handling**: Improved logging and error reporting
3. **Parallel Execution**: Build, test, and quality checks run in parallel
4. **GitHub Integration**: Native integration with GitHub ecosystem
5. **Local Development**: Easier to test pipeline changes locally

## File Changes

### New Files
- `.dagger/` - Complete Dagger module with TypeScript pipeline
- `.github/workflows/ci.yaml` - GitHub Actions workflow
- `MIGRATION.md` - This migration guide

### Modified Files
- `Jenkinsfile` - Replaced with deprecation notice
- `Earthfile` - Replaced with deprecation notice

### Removed Files
- `Jenkinsfile` - Deprecated CI configuration
- `Earthfile` - Deprecated build configuration

## Pipeline Comparison

### Earthly Targets → Dagger Functions

| Earthly Target | Dagger Function | Description |
|---------------|----------------|-------------|
| `+ci` | `ci()` | Complete CI pipeline |
| `+build` | `build()` | Build the project |
| `+test` | `test()` | Run tests |
| `+deps` | `deps()` | Resolve dependencies |
| N/A | `check()` | Run all checks (build+test+quality) |
| N/A | `artifact()` | Extract JAR artifact |
| N/A | `coverage()` | Generate coverage report |

### Jenkins Pipeline → GitHub Actions

| Jenkins Stage | GitHub Actions Step | Description |
|--------------|-------------------|-------------|
| `Build` | `Run Dagger CI pipeline` | Main CI execution |
| `archiveArtifacts` | `Upload artifacts` | Artifact storage |
| N/A | `Upload coverage report` | Coverage reporting |

## Environment Variables

### Before (Jenkins)
```groovy
environment {
    GITHUB_USERNAME = "shepherdjerred"
    GITHUB_TOKEN = credentials('GITHUB_TOKEN')
    EARTHLY_TOKEN = credentials('EARTHLY_TOKEN')
}
```

### After (GitHub Actions)
```yaml
env:
  GITHUB_TOKEN: ${{ secrets.GITHUB_TOKEN }}
  DAGGER_CLOUD_TOKEN: ${{ secrets.DAGGER_CLOUD_TOKEN }}
```

## Running Locally

### Before (Earthly)
```bash
earthly +ci
```

### After (Dagger)
```bash
cd .dagger
bun install
dagger call ci --source=.. --version="local" --git-sha="$(git rev-parse HEAD)"
```

## Required Secrets

Add these secrets to your GitHub repository:

1. `DAGGER_CLOUD_TOKEN` - For Dagger Cloud integration (optional)
2. `GITHUB_TOKEN` - Automatically provided by GitHub Actions

## Testing the Migration

1. **Local Testing**:
   ```bash
   cd .dagger
   bun install
   dagger call check --source=..
   ```

2. **CI Testing**:
   - Create a pull request to trigger the development pipeline
   - Merge to main to trigger the production pipeline

## Rollback Plan

If issues arise, you can temporarily revert by:

1. Check git history to restore the old `Jenkinsfile` and `Earthfile` if needed
2. Re-enable the Jenkins job (if still available)
3. Temporarily disable the GitHub Actions workflow

Note: The old `Jenkinsfile` and `Earthfile` have been removed but can be restored from git history if needed.

## Migration Status

✅ **COMPLETED** - The migration from Earthly/Jenkins to GitHub Actions/Dagger is complete!

### What Was Done

1. ✅ Created `.dagger/` module with TypeScript pipeline
2. ✅ Created `.github/workflows/ci.yaml` workflow
3. ✅ Removed deprecated `Jenkinsfile` and `Earthfile`
4. ✅ Updated code structure to match current best practices
5. ✅ Configured parallel execution for CI tasks
6. ✅ Set up artifact and coverage report uploads

### Next Steps

1. Monitor the first few CI runs on GitHub Actions
2. Verify artifact uploads work correctly
3. Test coverage reporting in production runs
4. Consider adding Dagger Cloud token for enhanced monitoring

## Support

For issues with the migration:
- Check the GitHub Actions logs
- Use `dagger call --help` for function documentation
- Reference the `.dagger/README.md` for detailed usage
