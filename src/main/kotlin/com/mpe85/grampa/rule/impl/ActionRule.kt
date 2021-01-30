package com.mpe85.grampa.rule.impl

import com.mpe85.grampa.context.ParserContext
import com.mpe85.grampa.context.RuleContext
import com.mpe85.grampa.rule.Rule
import com.mpe85.grampa.util.checkEquality
import com.mpe85.grampa.util.stringify
import java.util.Objects.hash

/**
 * An action rule implementation.
 *
 * @author mpe85
 * @param[T] The type of the stack elements
 * @property[action] The action that is executed by the rule
 * @property[skippable] Indicates if the action is skippable inside test rules
 */
public open class ActionRule<T> @JvmOverloads constructor(
    private val action: (RuleContext<T>) -> Boolean,
    private val skippable: Boolean = false
) : AbstractRule<T>() {

    override fun match(context: ParserContext<T>): Boolean = context.inTestRule && skippable || action(context)

    override fun hashCode(): Int = hash(super.hashCode(), action, skippable)
    override fun equals(other: Any?): Boolean =
        checkEquality(other, { super.equals(other) }, { it.action }, { it.skippable })

    override fun toString(): String = stringify("action" to action, "skippable" to skippable)
}

/**
 * Create a rule from this action.
 *
 * @return A [ActionRule]
 */
public fun <T> ((RuleContext<T>) -> Boolean).toRule(skippable: Boolean = false): Rule<T> = ActionRule(this, skippable)
