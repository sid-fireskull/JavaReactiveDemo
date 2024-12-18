package com.reactive.demo.ReactiveDemo;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;



@SpringBootApplication
public class ReactiveDemoApplication {

	public static void main(String[] args) {
		ReactiveDemoApplication reactiveDemoApplication = new ReactiveDemoApplication();

//        fluxAndMonoGeneratorService.namesFlux().subscribe(item -> System.out.println("Flux Name is: " + item));
//        fluxAndMonoGeneratorService.nameMono().subscribe(name -> System.out.println("Mono Name is: " + name));

		reactiveDemoApplication.namesFluxMap().subscribe(item -> System.out.println("Flux Map Name is: " + item));
		reactiveDemoApplication.namesFluxFilter(4).subscribe(item -> System.out.println("Flux Filter Name is: " + item));
//        fluxAndMonoGeneratorService.namesFluxImmutibility().subscribe(item -> System.out.println("Flux Im-mutibility Name is: " + item));
	}
	
	 Flux<String> namesFlux()
	    {
	        return Flux.fromIterable(List.of("John", "Claire","Tony"));
	                //.log();
	    }

	    Mono<String> namesMono()
	    {
	        return Mono.just("John");
	    }

	    Mono<String> namesMonoMapFilter()
	    {
	        return Mono.just("john")
	                .map(String::toUpperCase)
	                .filter(name -> name.length() <= 4)
	                .log();
	    }

	    Mono<List<String>> namesMonoFlatMap()
	    {
	        return Mono.just("john")
	                .filter(name -> name.length() <= 4)
	                .map(String::toUpperCase)
	                .flatMap(this::splitCharMono)
	                .log();
	    }

	    Flux<String> namesMonoFlatMapMany()
	    {
	        return Mono.just("john")
	                .filter(name -> name.length() <= 4)
	                .map(String::toUpperCase)
	                .flatMapMany(this::splitChar)
	                .log();
	    }


	    Flux<String> namesFluxFilter(int StringLength)
	    {
	        return Flux.fromIterable(List.of("John", "Claire","Tony"))
	                .filter(name -> name.length() <= StringLength)
	                .map(String::toUpperCase)
	                .log();
	    }

	    Flux<String> namesFluxMap()
	    {
	        return Flux.fromIterable(List.of("John", "Claire","Tony"))
	                .map(String::toUpperCase)
	                .log();
	    }

	    Flux<String> namesFluxImmutibility()
	    {
	        Flux<String> namesFlux = Flux.fromIterable(List.of("John", "Claire","Tony"));
	        namesFlux.map(String::toUpperCase);
	        return namesFlux;
	    }

	    Flux<String> namesFluxFlatMap()
	    {
	        return Flux.fromIterable(List.of("John", "Claire","Tony"))
	                .filter(name -> name.length() <= 4)
	                .flatMap(this::splitChar)
	                .log();
	    }

	    Flux<String> namesFluxFlatMapAsync()
	    {
	        return Flux.fromIterable(List.of("John", "Claire","Tony"))
	                .filter(name -> name.length() <= 4)
	                .flatMap(this::splitCharDelay)
	                .log();
	    }

	    Flux<String> namesFluxConcatMapAsync()
	    {
	        return Flux.fromIterable(List.of("John", "Claire","Tony"))
	                .filter(name -> name.length() <= 4)
	                .concatMap(this::splitCharDelay)
	                .log();
	    }

	    Flux<String> namesFluxFlatMapTransform()
	    {
	        Function<Flux<String>, Flux<String>> customFunc = e -> e.map(String::toUpperCase)
	                                                                .filter(str -> str.length()>4)
	                                                                .flatMap(this::splitChar);

	        return Flux.fromIterable(List.of("John", "Claire","Tony"))
	                .transform(customFunc)
	                .log();
	    }

	    Flux<String> namesFluxFlatMapTransformDefaultIfEmpty()
	    {
	        Function<Flux<String>, Flux<String>> customFunc = e -> e.map(String::toUpperCase)
	                .filter(str -> str.length()>6)
	                .flatMap(this::splitChar);

	        return Flux.fromIterable(List.of("John", "Claire","Tony"))
	                .transform(customFunc)
	                .defaultIfEmpty("Default")
	                .log();
	    }

	    Flux<String> namesFluxFlatMapTransformSwitchIfEmpty()
	    {
	        Function<Flux<String>, Flux<String>> customFunc = e -> e.map(String::toUpperCase)
	                .filter(str -> str.length()>6)
	                .flatMap(this::splitChar);

	        Flux<String> defaultFlux = Flux.just("Default").transform(customFunc);

	        return Flux.fromIterable(List.of("John", "Claire","Tony"))
	                .transform(customFunc)
	                .switchIfEmpty(defaultFlux)
	                .log();
	    }

	    /*
	     *  concat & concatwith subscribes publishers in a sequence
	     */
	    
	    Flux<String> fluxConcat()
	    {
	        Flux<String> male = Flux.just("John", "Steve","Tony");
	        Flux<String> female = Flux.just("Kate", "Natasa","Claire");
	        // Static method concat
	        return Flux.concat(male, female)
	                .log();
	    }

	    Flux<String> fluxConcatWith()
	    {
	        Flux<String> male = Flux.just("John", "Steve","Tony");
	        Flux<String> female = Flux.just("Kate", "Natasa","Claire");
	        // Instance method concat
	        return male.concatWith(female)
	                .log();
	    }

	    Flux<String> fluxConcatWithMono()
	    {
	        Mono<String> male = Mono.just("John");
	        Mono<String> female = Mono.just("Kate");
	        // Instance method concat
	        return male.concatWith(female)
	                .log();
	    }

	    /*
	     *  In case of merge & mergewith both publishers subscribe at sametime
	     */
	    
	    Flux<String> fluxMerge()
	    {
	        Flux<String> male = Flux.just("John", "Steve","Tony")
	                .delayElements(Duration.ofMillis(100));
	        Flux<String> female = Flux.just("Kate", "Natasa","Claire")
	                .delayElements(Duration.ofMillis(125));
	        // Static method merge
	        return Flux.merge(male, female)
	                .log();
	    }

	    Flux<String> fluxMergeWith()
	    {
	        Flux<String> male = Flux.just("John", "Steve","Tony")
	                .delayElements(Duration.ofMillis(100));
	        Flux<String> female = Flux.just("Kate", "Natasa","Claire")
	                .delayElements(Duration.ofMillis(125));
	        // Instance method fluxMergeWith
	        return male.mergeWith(female)
	                .log();
	    }

	    Flux<String> fluxMergeWithMono()
	    {
	        Mono<String> male = Mono.just("Steve");
	        Mono<String> female = Mono.just("Claire");
	        // Instance method fluxMergeWith
	        return male.mergeWith(female)
	                .log();
	    }

	    Flux<String> fluxMergeSeq()
	    {
	        Flux<String> male = Flux.just("John", "Steve","Tony")
	                .delayElements(Duration.ofMillis(100));
	        Flux<String> female = Flux.just("Kate", "Natasa","Claire")
	                .delayElements(Duration.ofMillis(125));
	        // Static method merge
	        return Flux.mergeSequential(male, female)
	                .log();
	    }

	    /* Note: zip & zipWith Wait For all publishers involve in the transformation 
	     * to emit one element and then move to next 
	     */
	    

	    Flux<String> fluxZip()
	    {
	        Flux<String> male = Flux.just("John", "Steve","Tony")
	                .delayElements(Duration.ofMillis(100));
	        Flux<String> female = Flux.just("Kate", "Natasa","Claire")
	                .delayElements(Duration.ofMillis(125));
	        // Static method merge
	        return Flux.zip(male, female)
	                .map(t2 -> t2.getT1() + " " + t2.getT2())
	                .log();
	    }

	    Flux<String> fluxZipWith()
	    {
	        Flux<String> male = Flux.just("John", "Steve","Tony")
	                .delayElements(Duration.ofMillis(100));
	        Flux<String> female = Flux.just("Kate", "Natasa","Claire")
	                .delayElements(Duration.ofMillis(125));
	        // Static method merge
	        return male.zipWith(female)
	                .map(t2 -> t2.getT1() + " " + t2.getT2())
	                .log();
	    }


	    Mono<String> fluxZipWithMono()
	    {
	        Mono<String> male = Mono.just("John");
	        Mono<String> female = Mono.just("Natasa");
	        // Static method merge
	        return male.zipWith(female)
	                .map(t2 -> t2.getT1() + " " + t2.getT2())
	                .log();
	    }



	// ---------------------------------------------------------------------------------


	    private Flux<String> splitChar(String name) {
	        String[] sp = name.split("");
	        return Flux.fromArray(sp);
	    }

	    private Flux<String> splitCharDelay(String name) {
	        String[] sp = name.split("");
	        int delay = new Random().nextInt(1000);
	        return Flux.fromArray(sp).delayElements(Duration.ofMillis(delay));
	    }

	    private Mono<List<String>> splitCharMono(String s) {
	        String[] str= s.split("");
	        return Mono.just(List.of(str));
	    }


}
