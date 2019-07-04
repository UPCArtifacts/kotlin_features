## sandy-8925-Checklist
----
#### Metrics provided by Detekt
* Number of lines of code 731
* Number of Kotlin files: 9
* Cyclomatic complexity: 66
* Cyclomatic complexity by thousands of lines: 167 

----
**7** features analyzed

*	<a href="#type_inference">Type Inference</a> 
*	<a href="#lambda">Lambda</a> 
*	<a href="#safe_call">Safe Call</a> 
*	<a href="#when_expr">When expression</a> 
*	<a href="#companion_object">Companion Object</a> 
*	<a href="#unsafe_call">Unsafe Call</a> 
*	<a href="#string_template">String Template</a> 


### <a name="type_inference">Type Inference</a>
----
#### Functions
* **Instability - Polinomial 4:** ![equation](http://latex.codecogs.com/svg.latex?-8.8e-05x%5E4%20&plus;%200.009671x%5E3%20&plus;-0.367829x%5E2%20&plus;%205.589749x%20&plus;%20-3.890635)
    * **R_Squared:** 0.62741768
* **Instability - Polinomial 3:** ![equation](http://latex.codecogs.com/svg.latex?('0.002095x%5E3%20&plus;-0.156736x%5E2%20&plus;%203.516236x%20&plus;%201.037747',))
    * **R_Squared:** 0.58762762
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?5.999323%5Clog_%7B3.426389%7D%28x%29%20&plus;%208.173114)
    * **R_Squared:** 0.42331146
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.235556x%20&plus;%2016.768873)
    * **R_Squared:** 0.1936727

**Plots** :chart_with_upwards_trend:
-----

![sandy-8925-Checklist T11_4](../plots/sandy-8925-Checklist_type_inference_T11_4.png)
![sandy-8925-Checklist T11_3](../plots/sandy-8925-Checklist_type_inference_T11_3.png)
![sandy-8925-Checklist T6](../plots/sandy-8925-Checklist_type_inference_T6.png)
![sandy-8925-Checklist T1](../plots/sandy-8925-Checklist_type_inference_T1.png)
### <a name="lambda">Lambda</a>
----
#### Functions
* **Plateau Gradual Rise - Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B-8.088235%7D%7B1%20&plus;%20%5Cepsilon%5E%28--19.086895%28x%20-6.1026%29%29%7D%20&plus;%2010.088235)
    * **R_Squared:** 0.97944049
* **Instability - Polinomial 3:** ![equation](http://latex.codecogs.com/svg.latex?('0.000948x%5E3%20&plus;-0.071834x%5E2%20&plus;%201.667448x%20&plus;%20-1.456549',))
    * **R_Squared:** 0.81594948
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?3.488791%5Clog_%7B3.718103%7D%28x%29%20&plus;%201.572829)
    * **R_Squared:** 0.64094161
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.144465x%20&plus;%205.938462)
    * **R_Squared:** 0.33955517

**Plots** :chart_with_upwards_trend:
-----

![sandy-8925-Checklist T7](../plots/sandy-8925-Checklist_lambda_T7.png)
![sandy-8925-Checklist T11_3](../plots/sandy-8925-Checklist_lambda_T11_3.png)
![sandy-8925-Checklist T6](../plots/sandy-8925-Checklist_lambda_T6.png)
![sandy-8925-Checklist T1](../plots/sandy-8925-Checklist_lambda_T1.png)
### <a name="safe_call">Safe Call</a>
----
#### Functions
* **Plateau Sudden Rise - Binary Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B-2.0%7D%7B1%20&plus;%20%5Cepsilon%5E%28--44.75936%28x%20-3.497665%29%29%7D%20&plus;%203.0)
    * **R_Squared:** 1.0
* **Instability - Polinomial 3:** ![equation](http://latex.codecogs.com/svg.latex?('0.000184x%5E3%20&plus;-0.013651x%5E2%20&plus;%200.303775x%20&plus;%201.082552',))
    * **R_Squared:** 0.66088563
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?1.00006%5Clog_%7B10.363878%7D%28x%29%20&plus;%201.664137)
    * **R_Squared:** 0.50391521
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.019861x%20&plus;%202.436585)
    * **R_Squared:** 0.20357143

**Plots** :chart_with_upwards_trend:
-----

![sandy-8925-Checklist T9](../plots/sandy-8925-Checklist_safe_call_T9.png)
![sandy-8925-Checklist T11_3](../plots/sandy-8925-Checklist_safe_call_T11_3.png)
![sandy-8925-Checklist T6](../plots/sandy-8925-Checklist_safe_call_T6.png)
![sandy-8925-Checklist T1](../plots/sandy-8925-Checklist_safe_call_T1.png)
### <a name="when_expr">When expression</a>
----
#### Functions
* **Instability - Polinomial 3:** ![equation](http://latex.codecogs.com/svg.latex?('0.00013x%5E3%20&plus;-0.007253x%5E2%20&plus;%200.118527x%20&plus;%201.470428',))
    * **R_Squared:** 0.77275046
* **Sudden Rise - Exponential:** ![equation](http://latex.codecogs.com/svg.latex?40.329437x%5E%7B1.199321%7D%20&plus;%201.925053)
    * **R_Squared:** 0.69349607
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.020825x%20&plus;%201.671312)
    * **R_Squared:** 0.41781392
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?0.983396%5Clog_%7B47.508777%7D%28x%29%20&plus;%201.40483)
    * **R_Squared:** 0.31940667
* **Plateau Sudden Rise - Binary Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B1836.656701%7D%7B1%20&plus;%20%5Cepsilon%5E%28-1.672093%28x%20--3.412923%29%29%7D%20&plus;%20-1834.504053)
    * **R_Squared:** 0.20516218

**Plots** :chart_with_upwards_trend:
-----

![sandy-8925-Checklist T11_3](../plots/sandy-8925-Checklist_when_expr_T11_3.png)
![sandy-8925-Checklist T4](../plots/sandy-8925-Checklist_when_expr_T4.png)
![sandy-8925-Checklist T1](../plots/sandy-8925-Checklist_when_expr_T1.png)
![sandy-8925-Checklist T6](../plots/sandy-8925-Checklist_when_expr_T6.png)
![sandy-8925-Checklist T9](../plots/sandy-8925-Checklist_when_expr_T9.png)
### <a name="companion_object">Companion Object</a>
----
#### Functions
* **Plateau Sudden Rise - Binary Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B0.833333%7D%7B1%20&plus;%20%5Cepsilon%5E%28-55.454246%28x%20-6.848753%29%29%7D%20&plus;%202.166667)
    * **R_Squared:** 0.42060988
* **Instability - Polinomial 3:** ![equation](http://latex.codecogs.com/svg.latex?('9.5e-05x%5E3%20&plus;-0.007101x%5E2%20&plus;%200.165689x%20&plus;%201.821716',))
    * **R_Squared:** 0.33651585
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?0.955459%5Clog_%7B24.505871%7D%28x%29%20&plus;%202.059084)
    * **R_Squared:** 0.29916181
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.017398x%20&plus;%202.529161)
    * **R_Squared:** 0.16580206

**Plots** :chart_with_upwards_trend:
-----

![sandy-8925-Checklist T9](../plots/sandy-8925-Checklist_companion_object_T9.png)
![sandy-8925-Checklist T11_3](../plots/sandy-8925-Checklist_companion_object_T11_3.png)
![sandy-8925-Checklist T6](../plots/sandy-8925-Checklist_companion_object_T6.png)
![sandy-8925-Checklist T1](../plots/sandy-8925-Checklist_companion_object_T1.png)
### <a name="unsafe_call">Unsafe Call</a>
----
#### Functions
* **Instability - Polinomial 4:** ![equation](http://latex.codecogs.com/svg.latex?8.4e-05x%5E4%20&plus;%20-0.007155x%5E3%20&plus;0.207731x%5E2%20&plus;%20-2.345485x%20&plus;%208.108108)
    * **R_Squared:** 0.48296633
* **Instability - Polinomial 3:** ![equation](http://latex.codecogs.com/svg.latex?('-0.000766x%5E3%20&plus;0.050224x%5E2%20&plus;%20-0.973192x%20&plus;%205.189189',))
    * **R_Squared:** 0.33755061
* **Constant Decline - Linear:** ![equation](http://latex.codecogs.com/svg.latex?-0.051209x%20&plus;%201.297297)
    * **R_Squared:** 0.07894737
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?0.0%5Clog_%7B544.690978%7D%28x%29%20&plus;%200.324324)
    * **R_Squared:** 0.0

**Plots** :chart_with_upwards_trend:
-----

![sandy-8925-Checklist T11_4](../plots/sandy-8925-Checklist_unsafe_call_T11_4.png)
![sandy-8925-Checklist T11_3](../plots/sandy-8925-Checklist_unsafe_call_T11_3.png)
![sandy-8925-Checklist T2](../plots/sandy-8925-Checklist_unsafe_call_T2.png)
![sandy-8925-Checklist T6](../plots/sandy-8925-Checklist_unsafe_call_T6.png)
### <a name="string_template">String Template</a>
----
#### Functions
* **Sudden Decline - Exponential:** ![equation](http://latex.codecogs.com/svg.latex?1.0x%5E%7B0.0%7D%20&plus;%200.0)
    * **R_Squared:** 1.0
* **Instability - Polinomial 3:** ![equation](http://latex.codecogs.com/svg.latex?('-6.4e-05x%5E3%20&plus;0.004185x%5E2%20&plus;%20-0.081099x%20&plus;%200.432432',))
    * **R_Squared:** 0.33755061
* **Constant Decline - Linear:** ![equation](http://latex.codecogs.com/svg.latex?-0.004267x%20&plus;%200.108108)
    * **R_Squared:** 0.07894737
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?0.0%5Clog_%7B8150.892966%7D%28x%29%20&plus;%200.027027)
    * **R_Squared:** -0.0

**Plots** :chart_with_upwards_trend:
-----

![sandy-8925-Checklist T5](../plots/sandy-8925-Checklist_string_template_T5.png)
![sandy-8925-Checklist T11_3](../plots/sandy-8925-Checklist_string_template_T11_3.png)
![sandy-8925-Checklist T2](../plots/sandy-8925-Checklist_string_template_T2.png)
![sandy-8925-Checklist T6](../plots/sandy-8925-Checklist_string_template_T6.png)
