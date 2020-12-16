package com.mpe85.grampa.rule.impl;

import com.mpe85.grampa.rule.ActionContext;
import org.junit.jupiter.api.Test;

import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ConditionalRuleTest {

    @Test
    public void equalsHashCodeToString() {
        final Predicate<ActionContext<String>> pred = ctx -> true;
        final EmptyRule<String> empty = new EmptyRule<>();
        final NeverRule<String> never = new NeverRule<>();

        final ConditionalRule<String> rule1 = new ConditionalRule<>(pred::test, empty, never);
        final ConditionalRule<String> rule2 = new ConditionalRule<>(pred::test, new EmptyRule<>(), new NeverRule<>());
        final ConditionalRule<String> rule3 = new ConditionalRule<>(pred::test, empty);

        assertEquals(rule2, rule1);
        assertNotEquals(rule3, rule1);
        assertNotEquals(new Object(), rule1);

        assertEquals(rule1.hashCode(), rule2.hashCode());
        assertNotEquals(rule1.hashCode(), rule3.hashCode());

        assertEquals(
                "ConditionalRule{#children=2, thenRule=EmptyRule{#children=0}, elseRule=NeverRule{#children=0}}",
                rule1.toString());
        assertEquals(
                "ConditionalRule{#children=2, thenRule=EmptyRule{#children=0}, elseRule=NeverRule{#children=0}}",
                rule2.toString());
        assertEquals(
                "ConditionalRule{#children=1, thenRule=EmptyRule{#children=0}, elseRule=null}",
                rule3.toString());
    }

}
