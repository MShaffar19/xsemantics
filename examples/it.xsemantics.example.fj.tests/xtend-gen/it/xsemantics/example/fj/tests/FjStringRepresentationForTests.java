package it.xsemantics.example.fj.tests;

import com.google.common.base.Objects;
import it.xsemantics.example.fj.fj.ClassType;
import it.xsemantics.example.fj.fj.Expression;
import it.xsemantics.example.fj.fj.Field;
import it.xsemantics.example.fj.fj.Member;
import it.xsemantics.example.fj.fj.New;
import it.xsemantics.example.fj.fj.Selection;
import it.xsemantics.example.fj.typing.FjStringRepresentation;
import java.util.Arrays;
import java.util.List;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.nodemodel.ICompositeNode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;

@SuppressWarnings("all")
public class FjStringRepresentationForTests extends FjStringRepresentation {
  public String stringRep(final EObject eObject) {
    final ICompositeNode node = NodeModelUtils.getNode(eObject);
    boolean _notEquals = (!Objects.equal(node, null));
    if (_notEquals) {
      return super.stringRep(eObject);
    } else {
      CharSequence _customRep = this.customRep(eObject);
      return _customRep.toString();
    }
  }
  
  public String stringRep(final Field f) {
    String _name = f.getName();
    return _name;
  }
  
  protected CharSequence _customRep(final EObject eObject) {
    String _stringRep = super.stringRep(eObject);
    return _stringRep;
  }
  
  protected CharSequence _customRep(final New exp) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("new ");
    ClassType _type = exp.getType();
    it.xsemantics.example.fj.fj.Class _classref = _type.getClassref();
    String _name = _classref.getName();
    _builder.append(_name, "");
    _builder.append("(");
    EList<Expression> _args = exp.getArgs();
    final Function1<Expression,String> _function = new Function1<Expression,String>() {
        public String apply(final Expression it) {
          String _string = FjStringRepresentationForTests.this.string(it);
          return _string;
        }
      };
    List<String> _map = ListExtensions.<Expression, String>map(_args, _function);
    String _join = IterableExtensions.join(_map, ", ");
    String _plus = (_builder + _join);
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append(")");
    String _plus_1 = (_plus + _builder_1.toString());
    return _plus_1;
  }
  
  protected CharSequence _customRep(final Selection exp) {
    StringConcatenation _builder = new StringConcatenation();
    Expression _receiver = exp.getReceiver();
    String _string = this.string(_receiver);
    _builder.append(_string, "");
    _builder.append(".");
    Member _message = exp.getMessage();
    String _string_1 = this.string(_message);
    _builder.append(_string_1, "");
    return _builder;
  }
  
  public CharSequence customRep(final EObject exp) {
    if (exp instanceof New) {
      return _customRep((New)exp);
    } else if (exp instanceof Selection) {
      return _customRep((Selection)exp);
    } else if (exp != null) {
      return _customRep(exp);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(exp).toString());
    }
  }
}