/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.localsearch.manipulator;

/**
 *
 * @author oberger
 */
public interface IConstraint<T> {
   
    public boolean check(T manipulated);
    
}
