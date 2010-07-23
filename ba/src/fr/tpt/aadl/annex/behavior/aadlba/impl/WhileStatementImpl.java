/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package fr.tpt.aadl.annex.behavior.aadlba.impl;

import fr.tpt.aadl.annex.behavior.aadlba.AadlBaPackage;
import fr.tpt.aadl.annex.behavior.aadlba.BehaviorActions;
import fr.tpt.aadl.annex.behavior.aadlba.ValueExpression;
import fr.tpt.aadl.annex.behavior.aadlba.WhileStatement;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>While Statement</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link fr.tpt.aadl.annex.behavior.aadlba.impl.WhileStatementImpl#getValueExpressionOwned <em>Value Expression Owned</em>}</li>
 *   <li>{@link fr.tpt.aadl.annex.behavior.aadlba.impl.WhileStatementImpl#getBehaviorActionsOwned <em>Behavior Actions Owned</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class WhileStatementImpl extends CondStatementImpl implements WhileStatement
{
   /**
    * The cached value of the '{@link #getValueExpressionOwned() <em>Value Expression Owned</em>}' containment reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getValueExpressionOwned()
    * @generated
    * @ordered
    */
   protected ValueExpression valueExpressionOwned;

   /**
    * The cached value of the '{@link #getBehaviorActionsOwned() <em>Behavior Actions Owned</em>}' containment reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getBehaviorActionsOwned()
    * @generated
    * @ordered
    */
   protected BehaviorActions behaviorActionsOwned;

   /**
    * This is true if the Behavior Actions Owned containment reference has been set.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   protected boolean behaviorActionsOwnedESet;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected WhileStatementImpl()
   {
      super();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   protected EClass eStaticClass()
   {
      return AadlBaPackage.Literals.WHILE_STATEMENT;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public ValueExpression getValueExpressionOwned()
   {
      return valueExpressionOwned;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public NotificationChain basicSetValueExpressionOwned(ValueExpression newValueExpressionOwned, NotificationChain msgs)
   {
      ValueExpression oldValueExpressionOwned = valueExpressionOwned;
      valueExpressionOwned = newValueExpressionOwned;
      if (eNotificationRequired())
      {
         ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, AadlBaPackage.WHILE_STATEMENT__VALUE_EXPRESSION_OWNED, oldValueExpressionOwned, newValueExpressionOwned);
         if (msgs == null) msgs = notification; else msgs.add(notification);
      }
      return msgs;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setValueExpressionOwned(ValueExpression newValueExpressionOwned)
   {
      if (newValueExpressionOwned != valueExpressionOwned)
      {
         NotificationChain msgs = null;
         if (valueExpressionOwned != null)
            msgs = ((InternalEObject)valueExpressionOwned).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - AadlBaPackage.WHILE_STATEMENT__VALUE_EXPRESSION_OWNED, null, msgs);
         if (newValueExpressionOwned != null)
            msgs = ((InternalEObject)newValueExpressionOwned).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - AadlBaPackage.WHILE_STATEMENT__VALUE_EXPRESSION_OWNED, null, msgs);
         msgs = basicSetValueExpressionOwned(newValueExpressionOwned, msgs);
         if (msgs != null) msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, AadlBaPackage.WHILE_STATEMENT__VALUE_EXPRESSION_OWNED, newValueExpressionOwned, newValueExpressionOwned));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public BehaviorActions getBehaviorActionsOwned()
   {
      return behaviorActionsOwned;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public NotificationChain basicSetBehaviorActionsOwned(BehaviorActions newBehaviorActionsOwned, NotificationChain msgs)
   {
      BehaviorActions oldBehaviorActionsOwned = behaviorActionsOwned;
      behaviorActionsOwned = newBehaviorActionsOwned;
      boolean oldBehaviorActionsOwnedESet = behaviorActionsOwnedESet;
      behaviorActionsOwnedESet = true;
      if (eNotificationRequired())
      {
         ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, AadlBaPackage.WHILE_STATEMENT__BEHAVIOR_ACTIONS_OWNED, oldBehaviorActionsOwned, newBehaviorActionsOwned, !oldBehaviorActionsOwnedESet);
         if (msgs == null) msgs = notification; else msgs.add(notification);
      }
      return msgs;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setBehaviorActionsOwned(BehaviorActions newBehaviorActionsOwned)
   {
      if (newBehaviorActionsOwned != behaviorActionsOwned)
      {
         NotificationChain msgs = null;
         if (behaviorActionsOwned != null)
            msgs = ((InternalEObject)behaviorActionsOwned).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - AadlBaPackage.WHILE_STATEMENT__BEHAVIOR_ACTIONS_OWNED, null, msgs);
         if (newBehaviorActionsOwned != null)
            msgs = ((InternalEObject)newBehaviorActionsOwned).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - AadlBaPackage.WHILE_STATEMENT__BEHAVIOR_ACTIONS_OWNED, null, msgs);
         msgs = basicSetBehaviorActionsOwned(newBehaviorActionsOwned, msgs);
         if (msgs != null) msgs.dispatch();
      }
      else
      {
         boolean oldBehaviorActionsOwnedESet = behaviorActionsOwnedESet;
         behaviorActionsOwnedESet = true;
         if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, AadlBaPackage.WHILE_STATEMENT__BEHAVIOR_ACTIONS_OWNED, newBehaviorActionsOwned, newBehaviorActionsOwned, !oldBehaviorActionsOwnedESet));
      }
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public NotificationChain basicUnsetBehaviorActionsOwned(NotificationChain msgs)
   {
      BehaviorActions oldBehaviorActionsOwned = behaviorActionsOwned;
      behaviorActionsOwned = null;
      boolean oldBehaviorActionsOwnedESet = behaviorActionsOwnedESet;
      behaviorActionsOwnedESet = false;
      if (eNotificationRequired())
      {
         ENotificationImpl notification = new ENotificationImpl(this, Notification.UNSET, AadlBaPackage.WHILE_STATEMENT__BEHAVIOR_ACTIONS_OWNED, oldBehaviorActionsOwned, null, oldBehaviorActionsOwnedESet);
         if (msgs == null) msgs = notification; else msgs.add(notification);
      }
      return msgs;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void unsetBehaviorActionsOwned()
   {
      if (behaviorActionsOwned != null)
      {
         NotificationChain msgs = null;
         msgs = ((InternalEObject)behaviorActionsOwned).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - AadlBaPackage.WHILE_STATEMENT__BEHAVIOR_ACTIONS_OWNED, null, msgs);
         msgs = basicUnsetBehaviorActionsOwned(msgs);
         if (msgs != null) msgs.dispatch();
      }
      else
      {
         boolean oldBehaviorActionsOwnedESet = behaviorActionsOwnedESet;
         behaviorActionsOwnedESet = false;
         if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, AadlBaPackage.WHILE_STATEMENT__BEHAVIOR_ACTIONS_OWNED, null, null, oldBehaviorActionsOwnedESet));
      }
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public boolean isSetBehaviorActionsOwned()
   {
      return behaviorActionsOwnedESet;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
   {
      switch (featureID)
      {
         case AadlBaPackage.WHILE_STATEMENT__VALUE_EXPRESSION_OWNED:
            return basicSetValueExpressionOwned(null, msgs);
         case AadlBaPackage.WHILE_STATEMENT__BEHAVIOR_ACTIONS_OWNED:
            return basicUnsetBehaviorActionsOwned(msgs);
      }
      return super.eInverseRemove(otherEnd, featureID, msgs);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public Object eGet(int featureID, boolean resolve, boolean coreType)
   {
      switch (featureID)
      {
         case AadlBaPackage.WHILE_STATEMENT__VALUE_EXPRESSION_OWNED:
            return getValueExpressionOwned();
         case AadlBaPackage.WHILE_STATEMENT__BEHAVIOR_ACTIONS_OWNED:
            return getBehaviorActionsOwned();
      }
      return super.eGet(featureID, resolve, coreType);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public void eSet(int featureID, Object newValue)
   {
      switch (featureID)
      {
         case AadlBaPackage.WHILE_STATEMENT__VALUE_EXPRESSION_OWNED:
            setValueExpressionOwned((ValueExpression)newValue);
            return;
         case AadlBaPackage.WHILE_STATEMENT__BEHAVIOR_ACTIONS_OWNED:
            setBehaviorActionsOwned((BehaviorActions)newValue);
            return;
      }
      super.eSet(featureID, newValue);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public void eUnset(int featureID)
   {
      switch (featureID)
      {
         case AadlBaPackage.WHILE_STATEMENT__VALUE_EXPRESSION_OWNED:
            setValueExpressionOwned((ValueExpression)null);
            return;
         case AadlBaPackage.WHILE_STATEMENT__BEHAVIOR_ACTIONS_OWNED:
            unsetBehaviorActionsOwned();
            return;
      }
      super.eUnset(featureID);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public boolean eIsSet(int featureID)
   {
      switch (featureID)
      {
         case AadlBaPackage.WHILE_STATEMENT__VALUE_EXPRESSION_OWNED:
            return valueExpressionOwned != null;
         case AadlBaPackage.WHILE_STATEMENT__BEHAVIOR_ACTIONS_OWNED:
            return isSetBehaviorActionsOwned();
      }
      return super.eIsSet(featureID);
   }

} //WhileStatementImpl
