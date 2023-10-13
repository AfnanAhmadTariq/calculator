class Calculator {
    constructor() {
      this.operators = "+-/*()";
    }
  
    calSimple(expression) {
      const postFix = this.postFix(expression);
      const operand = [];
      let index = 0;
  
      for (let i = 0; i < postFix.length; i++) {
        const current = postFix.substring(i, i + 1);
  
        if (this.operators.includes(current)) {
          operand.push(parseFloat(postFix.substring(index, i)));
          index = i + 1;
          operand.push(this.compute(operand.pop(), operand.pop(), current));
        }
      }
  
      return operand.pop();
    }
  
    postFix(expression) {
      let postFix = "";
      const operator = [];
      let index = 0;
  
      for (let i = 0; i < expression.length; i++) {
        const current = expression.substring(i, i + 1);
        const top = operator.length === 0 ? "" : operator[operator.length - 1];
  
        if (this.operators.includes(current)) {
          postFix += expression.substring(index, i);
          index = i + 1;
  
          if (current === "(") {
            operator.push(current);
          } else if (current === ")") {
            while (top !== "(") {
              postFix += operator.pop();
            }
            operator.pop();
          } else {
            while (
              operator.length !== 0 &&
              this.precendence(top) >= this.precendence(current) &&
              top !== "("
            ) {
              postFix += operator.pop();
            }
            operator.push(current);
          }
        }
      }
  
      while (operator.length !== 0) {
        postFix += operator.pop();
      }
  
      return postFix;
    }
  
    precendence(operator) {
      switch (operator) {
        case "*":
        case "/":
          return 2;
        case "+":
        case "-":
          return 1;
        default:
          return 0;
      }
    }
  
    compute(num2, num1, operator) {
      switch (operator) {
        case "+":
          return num1 + num2;
        case "-":
          return num1 - num2;
        case "*":
          return num1 * num2;
        case "/":
          return num1 / num2;
        default:
          return 0;
      }
    }
  }