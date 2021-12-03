1. 自动配置
2. 依赖起步

条件注解
• @Conditional
类条件
• @ConditionalOnClass
• @ConditionalOnMissingClass
属性条件
• @ConditionalOnProperty
Bean 条件
• @ConditionalOnBean
• @ConditionalOnMissingBean
• @ConditionalOnSingleCandidate
资源条件
• @ConditionalOnResource
Web 应⽤条件
• @ConditionalOnWebApplication
• @ConditionalOnNotWebApplication
其他条件
• @ConditionalOnExpression
• @ConditionalOnJava
• @ConditionalOnJndi
	@Conditional	匹配条件


执⾏顺序
• @AutoConfigureBefore
• @AutoConfigureAfter
• @AutoConfigureOrder


@Conditional派生注解	作用(都是判断是否符合指定的条件)


	
	
	
	
	
	
	
	
	
	
	
	
