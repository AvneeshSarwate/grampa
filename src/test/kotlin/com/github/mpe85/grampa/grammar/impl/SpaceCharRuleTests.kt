package com.github.mpe85.grampa.grammar.impl

import com.github.mpe85.grampa.legalCodePoints
import com.github.mpe85.grampa.parser.Parser
import com.ibm.icu.lang.UCharacter
import com.ibm.icu.lang.UCharacter.toString
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.property.arbitrary.filter
import io.kotest.property.arbitrary.filterNot
import io.kotest.property.checkAll

class SpaceCharRuleTests : StringSpec({
    "SpaceChar rule matches all space char codepoints" {
        Parser(object : AbstractGrammar<Unit>() {
            override fun root() = spaceChar()
        }).apply {
            checkAll(legalCodePoints().filter { UCharacter.isSpaceChar(it.value) }) { cp ->
                run(toString(cp.value)).apply {
                    matched shouldBe true
                    matchedEntireInput shouldBe true
                    matchedInput shouldBe toString(cp.value)
                    restOfInput shouldBe ""
                }
            }
        }
    }
    "SpaceChar rule does not match non space char codepoints" {
        Parser(object : AbstractGrammar<Unit>() {
            override fun root() = spaceChar()
        }).apply {
            checkAll(legalCodePoints().filterNot { UCharacter.isSpaceChar(it.value) }) { cp ->
                run(toString(cp.value)).apply {
                    matched shouldBe false
                    matchedEntireInput shouldBe false
                    matchedInput shouldBe null
                    restOfInput shouldBe toString(cp.value)
                }
            }
        }
    }
    "SpaceChar rule does not match empty input" {
        Parser(object : AbstractGrammar<Unit>() {
            override fun root() = spaceChar()
        }).run("").apply {
            matched shouldBe false
            matchedEntireInput shouldBe false
            matchedInput shouldBe null
            restOfInput shouldBe ""
        }
    }
})
