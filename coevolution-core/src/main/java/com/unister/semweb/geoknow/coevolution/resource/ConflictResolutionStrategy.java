package com.unister.semweb.geoknow.coevolution.resource;

/**
 * Heuristics for applying change requests with potential conflicts / inconsistency
 */
public enum ConflictResolutionStrategy {

    /**
     * The change request will be applied regardless of a potential conflict (default behaviour)
     */
    IgnoreConflict,
    /**
     * If a potential conflict has been the detected, the change request will not be applied
     */
    IgnoreEntireChange,
    /**
     * The change request will be applied, but removals and additions of the potential conflicting statement will be
     * ignored
     */
    IgnoreChangeForConflictingPredicate,
    /**
     * If a potential conflict has been detected, the change request will be applied. In case the conflict occurred on
     * change statements with a single removal and addition of a certain predicate and the target resource contains
     * exactly one statement for this predicate with a different value ("cardinality one conflict")
     */
    ForceChange,
    /**
     * Similar to "force change", but for "cardinality one conflicts" the change applier will attempt to merge the
     * values of the change addition and target resource statements for certain defined datatypes
     */
    MergeChange;

    /**
     * to be used in @ApiParam allowableValues attribute
     */
    public static final String ALLOWABLE_VALUES = "IgnoreConflict,IgnoreEntireChange,IgnoreChangeForConflictingPredicate,ForceChange,MergeChange";
    
    /**
     * default strategy
     */
    public static final ConflictResolutionStrategy DEFAULT = IgnoreConflict;

}
