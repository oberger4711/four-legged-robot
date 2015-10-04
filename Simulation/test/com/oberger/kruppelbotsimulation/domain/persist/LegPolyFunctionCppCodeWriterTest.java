/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.domain.persist;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author oberger
 */
public class LegPolyFunctionCppCodeWriterTest {
    
    private LegPolyFunctionCppCodeWriter createTestee() {
	return new LegPolyFunctionCppCodeWriter();
    }

    @Test
    public void getFileNameBase_WithCppFile_ReturnsBase() {
	LegPolyFunctionCppCodeWriter testee = createTestee();
	
	String base = testee.getFileNameBase("asdf/asdf/asdf.cpp");
	
	Assert.assertEquals("asdf/asdf/asdf", base);
    }
    
    @Test
    public void getFileNameBase_WithFileWithoutEnding_ReturnsBase() {
	LegPolyFunctionCppCodeWriter testee = createTestee();
	
	String base = testee.getFileNameBase("asdf/asdf/asdf");
	
	Assert.assertEquals("asdf/asdf/asdf", base);
    }
    
}
