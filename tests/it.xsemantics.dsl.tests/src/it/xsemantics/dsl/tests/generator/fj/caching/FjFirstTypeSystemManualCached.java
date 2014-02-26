package it.xsemantics.dsl.tests.generator.fj.caching;

import it.xsemantics.example.fj.fj.Expression;
import it.xsemantics.example.fj.fj.Type;
import it.xsemantics.runtime.Result;
import it.xsemantics.runtime.RuleApplicationTrace;
import it.xsemantics.runtime.RuleEnvironment;
import it.xsemantics.runtime.caching.XsemanticsCache;
import it.xsemantics.runtime.caching.XsemanticsCachedData;
import it.xsemantics.test.fj.first.FjFirstTypeSystem;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class FjFirstTypeSystemManualCached extends FjFirstTypeSystem {

	@Inject
	XsemanticsCache cache;

	@Override
	protected Result<Type> typeInternal(final RuleEnvironment _environment_,
			final RuleApplicationTrace _trace_, final Expression expression) {
		return cache.get("typeInternal",_environment_, _trace_, new Provider<XsemanticsCachedData<Result<Type>>>() {
			public XsemanticsCachedData<Result<Type>> get() {
				return new XsemanticsCachedData<Result<Type>>(_environment_, _trace_, 
						FjFirstTypeSystemManualCached.super.typeInternal
							(_environment_, _trace_, expression));
			}
		}, expression);
	}
}
