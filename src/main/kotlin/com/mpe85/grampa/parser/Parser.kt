package com.mpe85.grampa.parser

import com.mpe85.grampa.context.impl.Context
import com.mpe85.grampa.context.impl.ContextState
import com.mpe85.grampa.event.ParseEventListener
import com.mpe85.grampa.event.PostParseEvent
import com.mpe85.grampa.event.PreParseEvent
import com.mpe85.grampa.grammar.Grammar
import com.mpe85.grampa.input.InputBuffer
import com.mpe85.grampa.input.impl.CharSequenceInputBuffer
import com.mpe85.grampa.stack.impl.LinkedListRestorableStack
import org.greenrobot.eventbus.EventBus

/**
 * The parser that parses an input text using a given parser grammar.
 *
 * @author mpe85
 * @param[T] The type of the stack elements
 * @param[grammar] A grammar instance
 * @property[rootRule] The root rule of the parser's grammar
 * @property[bus] The parser's event bus
 * @property[stack] The parser's value stack
 */
public class Parser<T>(grammar: Grammar<T>) {

    private val rootRule = grammar.root()
    private val bus = EventBus.builder().logNoSubscriberMessages(false).build()
    private var stack = LinkedListRestorableStack<T>()

    /**
     * Register a listener to the parser event bus.
     *
     * @param[listener] A parse event listener
     */
    public fun registerListener(listener: ParseEventListener<T>): Unit = bus.register(listener)

    /**
     * Unregister a listener from the parser event bus.
     *
     * @param[listener] A parse event listener
     */
    public fun unregisterListener(listener: ParseEventListener<T>): Unit = bus.unregister(listener)

    /**
     * Run the parser against a character sequence.
     *
     * @param[charSequence] A character sequence
     * @return The parse result
     */
    public fun run(charSequence: CharSequence): ParseResult<T> = run(CharSequenceInputBuffer(charSequence))

    /**
     * Run the parser against an input buffer. This function must only be called once.
     *
     * @param[inputBuffer] An input buffer
     * @return The parse result
     */
    public fun run(inputBuffer: InputBuffer): ParseResult<T> = createRootContext(inputBuffer).let { ctx ->
        bus.post(PreParseEvent(ctx))
        ParseResult(ctx.run(), ctx).also { bus.post(PostParseEvent(it)) }
    }

    /**
     * Create the initial root context for the parser's root rule.
     *
     * @param[inputBuffer] An input buffer
     * @return A rule context
     */
    private fun createRootContext(inputBuffer: InputBuffer) = stack.run {
        reset()
        Context(ContextState(inputBuffer, 0, rootRule, 0, this, bus))
    }
}
