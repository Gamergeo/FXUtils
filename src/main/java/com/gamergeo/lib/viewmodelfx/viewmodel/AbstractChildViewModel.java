package com.gamergeo.lib.viewmodelfx.viewmodel;

public abstract class AbstractChildViewModel<VM extends ViewModel> extends AbstractViewModel implements ChildViewModel<VM> {
   protected VM parent;

   public void setParent(VM parent) {
      this.parent = parent;
   }

   public boolean hasParent() {
      return true;
   }
}
