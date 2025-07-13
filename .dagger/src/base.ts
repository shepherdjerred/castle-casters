import { Container, dag } from "@dagger.io/dagger";

// Helper function to log with timestamp
export function logWithTimestamp(message: string): void {
  console.log(`[${new Date().toISOString()}] ${message}`);
}

// Helper function to measure execution time
export async function withTiming<T>(
  operation: string,
  fn: () => Promise<T>
): Promise<T> {
  const start = Date.now();
  logWithTimestamp(`Starting ${operation}...`);
  try {
    const result = await fn();
    const duration = Date.now() - start;
    logWithTimestamp(`✅ ${operation} completed in ${duration.toString()}ms`);
    return result;
  } catch (error) {
    const duration = Date.now() - start;
    logWithTimestamp(
      `❌ ${operation} failed after ${duration.toString()}ms: ${error instanceof Error ? error.message : String(error)}`
    );
    throw error;
  }
}

/**
 * Get a Maven container with Java 21 and Maven 3
 */
export function getMavenContainer(): Container {
  return dag
    .container()
    .from("maven:3-amazoncorretto-21")
    .withWorkdir("/workspace");
}

/**
 * Get a Maven container with cache mounted
 */
export function getMavenContainerWithCache(): Container {
  return getMavenContainer()
    .withMountedCache("/root/.m2/repository", dag.cacheVolume("maven-cache"));
}
