package com.reactive.demo.ReactiveDemo;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
class ReactiveDemoApplicationTests {

	ReactiveDemoApplication reactiveDemoApplication = new ReactiveDemoApplication();

    @Test
    void namesFluxTest()
    {
       Flux<String> namesFlux = reactiveDemoApplication.namesFlux();


        StepVerifier.create(namesFlux)
//                .expectNext("John", "Claire","Tony")
                .expectNextCount(3)
                .verifyComplete();
    }

    @Test
    void namesMonoTest()
    {
        Mono<String> namesMono = reactiveDemoApplication.namesMono();
        StepVerifier.create(namesMono).
                expectNext("John")
                .verifyComplete();
    }

    @Test
    void namesFluxMapTest() {
        Flux<String> namesFluxMapFlux = reactiveDemoApplication.namesFluxMap();
        StepVerifier.create(namesFluxMapFlux)
                .expectNext("JOHN")
                .expectNextCount(2)
                .verifyComplete();
    }

    @Test
    void namesFluxImmutibilityTest() {
        Flux<String> namesFluxMapFlux = reactiveDemoApplication.namesFluxImmutibility();
        StepVerifier.create(namesFluxMapFlux)
                .expectNext("John")
                .expectNextCount(2)
                .verifyComplete();
    }

    @Test
    void namesFluxFilter() {
        Flux<String> namesFluxFilterFlux = reactiveDemoApplication.namesFluxFilter(4);
        StepVerifier.create(namesFluxFilterFlux)
                .expectNext("JOHN")
                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    void namesFluxFlatMap() {
        Flux<String> namesFluxFilterFlux = reactiveDemoApplication.namesFluxFlatMap();
        StepVerifier.create(namesFluxFilterFlux)
                .expectNext("J","o","h","n","T","o","n","y")
                .verifyComplete();
    }


    @Test
    void namesFluxFlatMapAsync() {
        Flux<String> namesFluxFlatMapAsync = reactiveDemoApplication.namesFluxFlatMapAsync();
        StepVerifier.create(namesFluxFlatMapAsync)
                .expectNextCount(8)
                .verifyComplete();
    }

    @Test
    void namesFluxConcatMapAsync() {
        Flux<String> namesFluxConcatMapAsync = reactiveDemoApplication.namesFluxConcatMapAsync();
        StepVerifier.create(namesFluxConcatMapAsync)
                .expectNextCount(8)
                .verifyComplete();
    }

    @Test
    void namesMonoMapFilter() {
        Mono<String> namesMonoMapFilter = reactiveDemoApplication.namesMonoMapFilter();
        StepVerifier.create(namesMonoMapFilter)
                .expectNext("JOHN")
                .verifyComplete();
    }

    @Test
    void namesMonoFlatMap() {
        Mono<List<String>> namesMonoFlatMap = reactiveDemoApplication.namesMonoFlatMap();
        StepVerifier.create(namesMonoFlatMap)
                .expectNext(List.of("J","O","H","N"))
                .verifyComplete();
    }

    @Test
    void namesMonoFlatMapMany() {
        Flux<String> namesMonoFlatMapMany = reactiveDemoApplication.namesMonoFlatMapMany();
        StepVerifier.create(namesMonoFlatMapMany)
                .expectNext("J")
                .expectNextCount(3)
                .verifyComplete();
    }

    @Test
    void namesFluxFlatMapTransform() {
        Flux<String> namesFluxFlatMapTransform = reactiveDemoApplication.namesFluxFlatMapTransform();
        StepVerifier.create(namesFluxFlatMapTransform)
                .expectNext("C")
                .expectNextCount(5)
                .verifyComplete();
    }

    @Test
    void namesFluxFlatMapTransformDefaultIfEmpty() {
        Flux<String> namesFluxFlatMapTransformDefaultIfEmpty = reactiveDemoApplication.namesFluxFlatMapTransformDefaultIfEmpty();
        StepVerifier.create(namesFluxFlatMapTransformDefaultIfEmpty)
                .expectNext("Default")
                .verifyComplete();
    }

    @Test
    void namesFluxFlatMapTransformSwitchIfEmpty() {
        Flux<String> namesFluxFlatMapTransformSwitchIfEmpty = reactiveDemoApplication.namesFluxFlatMapTransformSwitchIfEmpty();
        StepVerifier.create(namesFluxFlatMapTransformSwitchIfEmpty)
                .expectNextCount(7)
                .verifyComplete();
    }

    @Test
    void fluxConcat() {
        Flux<String> fluxConcat = reactiveDemoApplication.fluxConcat();
        StepVerifier.create(fluxConcat)
                .expectNextCount(6)
                .verifyComplete();
    }

    @Test
    void fluxConcatWith() {
        Flux<String> fluxConcatWith = reactiveDemoApplication.fluxConcatWith();
        StepVerifier.create(fluxConcatWith)
                .expectNext("John")
                .expectNextCount(5)
                .verifyComplete();
    }


    @Test
    void fluxConcatWithMono() {
        Flux<String> fluxConcatWithMono = reactiveDemoApplication.fluxConcatWithMono();
        StepVerifier.create(fluxConcatWithMono)
                .expectNext("John", "Kate")
                .verifyComplete();
    }

    @Test
    void fluxMerge() {
        Flux<String> fluxMerge = reactiveDemoApplication.fluxMerge();
        StepVerifier.create(fluxMerge)
                .expectNext("John", "Kate")
                .expectNextCount(4)
                .verifyComplete();
    }


    @Test
    void fluxMergeWith() {
        Flux<String> fluxMergeWith = reactiveDemoApplication.fluxMergeWith();
        StepVerifier.create(fluxMergeWith)
                .expectNext("John")
                .expectNext("Kate")
                .expectNextCount(4)
                .verifyComplete();
    }

    @Test
    void fluxMergeWithMono() {
        Flux<String> fluxMergeWithMono = reactiveDemoApplication.fluxMergeWithMono();
        StepVerifier.create(fluxMergeWithMono)
                .expectNextCount(2)
                .verifyComplete();
    }

    @Test
    void fluxMergeSeq() {
        Flux<String> fluxMergeSeq = reactiveDemoApplication.fluxMergeSeq();
        StepVerifier.create(fluxMergeSeq)
                .expectNext("John", "Steve")
                .expectNextCount(4)
                .verifyComplete();
    }

    @Test
    void fluxZip() {
        Flux<String> fluxMergeSeq = reactiveDemoApplication.fluxZip();
        StepVerifier.create(fluxMergeSeq)
                .expectNext("John Kate")
                .expectNextCount(2)
                .verifyComplete();
    }

    @Test
    void fluxZipWith() {
        Flux<String> fluxZipWith = reactiveDemoApplication.fluxZipWith();
        StepVerifier.create(fluxZipWith)
                .expectNext("John Kate")
                .expectNextCount(2)
                .verifyComplete();
    }

    @Test
    void fluxZipWithMono() {
        Mono<String> fluxZipWithMono = reactiveDemoApplication.fluxZipWithMono();
        StepVerifier.create(fluxZipWithMono)
                .expectNext("John Natasa")
                .verifyComplete();
    }

}
