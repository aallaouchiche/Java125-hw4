package com.scg.domain;

import org.junit.Test;

public class TestSkill
{
    private final String[]  allSkillNames   =
    {
        "PROJECT_MANAGER",
        "SOFTWARE_ENGINEER",
        "SOFTWARE_TESTER",
        "SYSTEM_ARCHITECT"
    };
    
    @Test
    public void test()
    {
        testValueOf();
        testValues();
    }

    private void testValueOf()
    {
        for ( String str : allSkillNames )
        {
            Skill   skill   = Skill.valueOf( str );
            System.out.println( skill );
        }
    }
    
    private void testValues()
    {
        Skill[] skills  = Skill.values();
        for ( Skill skill : skills )
            System.out.println( skill );
    }
    
}
