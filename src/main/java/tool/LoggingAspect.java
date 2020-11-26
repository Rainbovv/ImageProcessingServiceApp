package tool;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class LoggingAspect {

	private Benchmark benchmark;


	@After("execution(* transform(String))")
	public void processingFinished(JoinPoint jp){
		System.out.printf("%s has been processed in %f sec.%n",jp.getArgs()[0], benchmark.finish());
	}

	@Before("execution(* transform(String))")
	public void processing(JoinPoint jp) {
		System.out.println("Unprocessed file found.\nProcessing file >> " + jp.getArgs()[0]);
		this.benchmark = new Benchmark();
		benchmark.start();
	}


}
