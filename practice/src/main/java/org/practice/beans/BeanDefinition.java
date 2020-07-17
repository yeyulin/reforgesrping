package org.practice.beans;

public interface BeanDefinition {
    String SCOPE_SINGLETON = "singleton";
    String SCOPE_PROTOTYPE = "prototype";
    String SCOPE_DEFAULT = "";

    /**
     * Return whether this a <b>Singleton</b>, with a single, shared instance
     * returned on all calls.
     *
     * @see #SCOPE_SINGLETON
     */
     boolean isSingleton();

    /**
     * Return whether this a <b>Prototype</b>, with an independent instance
     * returned for each call.
     *
     * @see #SCOPE_PROTOTYPE
     */
     boolean isPrototype();

    /**
     * getScope
     * @return
     */
    String getScope();

    /**
     * setScope
     * @param scope
     */
    void setScope(String scope);

    /**
     * 得到bean的具体路径
     *
     * @return
     */
    String getBeanClassName();

    /**
     * getBeanName
     * @return
     */
    String getBeanName();

}
