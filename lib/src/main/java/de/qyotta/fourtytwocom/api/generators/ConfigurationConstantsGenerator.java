/*******************************************************************************
 * Qyotta UG (haftungsbeschränkt) (http://www.qyotta.de).
 * All rights reserved.
 * Copyright (c) 2012 Qyotta UG
 *******************************************************************************/
package de.qyotta.fourtytwocom.api.generators;

import java.io.PrintWriter;
import java.lang.reflect.Method;

import com.google.gwt.core.ext.ConfigurationProperty;
import com.google.gwt.core.ext.Generator;
import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.PropertyOracle;
import com.google.gwt.core.ext.SelectionProperty;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.TypeOracle;
import com.google.gwt.user.rebind.ClassSourceFileComposerFactory;
import com.google.gwt.user.rebind.SourceWriter;

import de.qyotta.fourtytwocom.api.client.utils.ConfigurationConstants;

public class ConfigurationConstantsGenerator extends Generator {

   /** {@inheritDoc} */
   @Override
   public String generate(final TreeLogger logger, final GeneratorContext context, final String typeName) throws UnableToCompleteException {

      final PropertyOracle propertyOracle = context.getPropertyOracle();
      final TypeOracle typeOracle = context.getTypeOracle();
      assert typeOracle != null;

      final JClassType classType = typeOracle.findType(typeName);

      // get the package name
      final String packageName = classType.getPackage().getName();
      // build name for implementation class
      final String simpleName = classType.getSimpleSourceName() + "Impl"; //$NON-NLS-1$
      // combine package name and simple name to full qualified
      final String fullName = packageName + "." + simpleName; //$NON-NLS-1$

      final ClassSourceFileComposerFactory composer = new ClassSourceFileComposerFactory(packageName, simpleName);
      composer.addImplementedInterface(typeName);
      composer.addImport(typeName);

      final PrintWriter printWriter = context.tryCreate(logger, packageName, simpleName);

      if (printWriter == null) {
         return fullName;
      }

      // start writing the implementation
      final SourceWriter writer = composer.createSourceWriter(context, printWriter);

      for (final Method method : ConfigurationConstants.class.getMethods()) {
         try {

            if (method.getReturnType().equals(boolean.class)) {
               final SelectionProperty selectionProperty = propertyOracle.getSelectionProperty(logger, method.getName());
               generateBooleanMethod(method, selectionProperty, writer);
               continue;
            }
            if (method.getReturnType().equals(String.class)) {
               final ConfigurationProperty configurationProperty = propertyOracle.getConfigurationProperty(method.getName());
               generateStringMethod(method, configurationProperty, writer);
               continue;
            }
            logger.log(TreeLogger.ERROR, "Return type +'" + method.getReturnType().toString() + "' not supported!"); //$NON-NLS-1$ //$NON-NLS-2$
            throw new IllegalArgumentException("Return type +'" + method.getReturnType().toString() + "' not supported!"); //$NON-NLS-1$ //$NON-NLS-2$

         } catch (final Exception e) {
            throw new UnableToCompleteException();
         }
      }

      writer.commit(logger);

      return fullName;

   }

   private void generateBooleanMethod(final Method method, final SelectionProperty selectionProperty, final SourceWriter writer) {
      writer.println("public boolean " + method.getName() + "() {"); //$NON-NLS-1$ //$NON-NLS-2$
      writer.println("return " + Boolean.valueOf(selectionProperty.getCurrentValue()) + ";"); //$NON-NLS-1$//$NON-NLS-2$
      writer.println("}"); //$NON-NLS-1$
   }

   private void generateStringMethod(final Method method, final ConfigurationProperty configurationProperty, final SourceWriter writer) {
      writer.println("public String " + method.getName() + "() {"); //$NON-NLS-1$ //$NON-NLS-2$
      writer.println("return " + "\"" + configurationProperty.getValues().get(0) + "\"" + ";"); //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
      writer.println("}"); //$NON-NLS-1$
   }
}
