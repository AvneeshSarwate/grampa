package com.mpe85.grampa.rule

/**
 * A rule that references another rule.
 *
 * @author mpe85
 * @property[referencedRuleHash] The hash value of the referenced rule
 */
public interface ReferenceRule<T> : Rule<T> {

    public val referencedRuleHash: Int
}
