package tool;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class BenchmarkAspect {



	@Around("execution(* transform(String))")
	public void bench(ProceedingJoinPoint pjp) throws Throwable {
		double startTime = ((double) System.nanoTime() / 1_000_000_000);
		pjp.proceed();
		System.out.printf("Processing time - %f sec.%n",
				(((double)System.nanoTime() / 1_000_000_000) - startTime));
	}
}
