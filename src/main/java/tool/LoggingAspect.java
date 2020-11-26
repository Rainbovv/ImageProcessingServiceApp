package tool;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Aspect
public class LoggingAspect {

//	private Benchmark benchmark;


	@Around("execution(* transform(String))")
	public void processing(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		System.out.println("\nProcessing file >> " + proceedingJoinPoint.getArgs()[0]);
		proceedingJoinPoint.proceed();
		System.out.println(proceedingJoinPoint.getArgs()[0] + " has been processed!");
	}

//	@After("execution(* transform(String))")
//	public void processingFinished(JoinPoint jp){
//		System.out.printf("%s has been processed in %f sec.%n",jp.getArgs()[0], benchmark.finish());
//	}
//
//	@Before("execution(* transform(String))")
//	public void processing(JoinPoint jp) {
//		System.out.println("Unprocessed file found.\nProcessing file >> " + jp.getArgs()[0]);
//		this.benchmark = new Benchmark();
//		benchmark.start();
//	}


}
