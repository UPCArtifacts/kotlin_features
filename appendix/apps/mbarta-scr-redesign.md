## mbarta-scr-redesign
----
#### Metrics provided by Detekt
* Number of lines of code 1198
* Number of Kotlin files: 36
* Cyclomatic complexity: 108
* Cyclomatic complexity by thousands of lines: 217 

----
**8** features analyzed

*	<a href="#type_inference">Type Inference</a> 
*	<a href="#lambda">Lambda</a> 
*	<a href="#safe_call">Safe Call</a> 
*	<a href="#companion_object">Companion Object</a> 
*	<a href="#string_template">String Template</a> 
*	<a href="#func_with_default_value">Function with Default Value</a> 
*	<a href="#smart_cast">Smart Cast</a> 
*	<a href="#extension_function">Extension Function</a> 


### <a name="type_inference">Type Inference</a>
----
#### Functions
* **Instability - Polinomial 4:** ![equation](http://latex.codecogs.com/svg.latex?0.000626x%5E4%20&plus;%20-0.042797x%5E3%20&plus;0.856774x%5E2%20&plus;%20-2.630632x%20&plus;%206.835474)
    * **R_Squared:** 0.98749607
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?2.62913x%20&plus;%201.51087)
    * **R_Squared:** 0.92393333
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?18.093605%5Clog_%7B3.145508%7D%28x%29%20&plus;%200.0)
    * **R_Squared:** 0.78688636

**Plots** :chart_with_upwards_trend:
-----

![mbarta-scr-redesign T11_4](../plots/mbarta-scr-redesign_type_inference_T11_4.png)
![mbarta-scr-redesign T1](../plots/mbarta-scr-redesign_type_inference_T1.png)
![mbarta-scr-redesign T6](../plots/mbarta-scr-redesign_type_inference_T6.png)
### <a name="lambda">Lambda</a>
----
#### Functions
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?4.726909%5Clog_%7B3.691714%7D%28x%29%20&plus;%202.655296)
    * **R_Squared:** 0.92002637
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.416522x%20&plus;%205.710145)
    * **R_Squared:** 0.88345644

**Plots** :chart_with_upwards_trend:
-----

![mbarta-scr-redesign T6](../plots/mbarta-scr-redesign_lambda_T6.png)
![mbarta-scr-redesign T1](../plots/mbarta-scr-redesign_lambda_T1.png)
### <a name="safe_call">Safe Call</a>
----
#### Functions
* **Plateau Gradual Rise - Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B-9.427511%7D%7B1%20&plus;%20%5Cepsilon%5E%28--0.578529%28x%20-11.537446%29%29%7D%20&plus;%2011.961409)
    * **R_Squared:** 0.95665104
* **Instability - Polinomial 3:** ![equation](http://latex.codecogs.com/svg.latex?('-0.003244x%5E3%20&plus;0.11213x%5E2%20&plus;%20-0.45103x%20&plus;%202.533597',))
    * **R_Squared:** 0.94497633
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.552609x%20&plus;%200.717391)
    * **R_Squared:** 0.89217612
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?4.306899%5Clog_%7B3.476405%7D%28x%29%20&plus;%200.0)
    * **R_Squared:** 0.73249988

**Plots** :chart_with_upwards_trend:
-----

![mbarta-scr-redesign T7](../plots/mbarta-scr-redesign_safe_call_T7.png)
![mbarta-scr-redesign T11_3](../plots/mbarta-scr-redesign_safe_call_T11_3.png)
![mbarta-scr-redesign T1](../plots/mbarta-scr-redesign_safe_call_T1.png)
![mbarta-scr-redesign T6](../plots/mbarta-scr-redesign_safe_call_T6.png)
### <a name="companion_object">Companion Object</a>
----
#### Functions
* **Plateau Gradual Rise - Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B2.658595%7D%7B1%20&plus;%20%5Cepsilon%5E%28-0.278506%28x%20-6.369774%29%29%7D%20&plus;%201.434774)
    * **R_Squared:** 0.90769524
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?1.122621%5Clog_%7B3.718282%7D%28x%29%20&plus;%201.423672)
    * **R_Squared:** 0.85076235
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.096957x%20&plus;%202.163043)
    * **R_Squared:** 0.79344236

**Plots** :chart_with_upwards_trend:
-----

![mbarta-scr-redesign T7](../plots/mbarta-scr-redesign_companion_object_T7.png)
![mbarta-scr-redesign T6](../plots/mbarta-scr-redesign_companion_object_T6.png)
![mbarta-scr-redesign T1](../plots/mbarta-scr-redesign_companion_object_T1.png)
### <a name="string_template">String Template</a>
----
#### Functions
* **Instability - Polinomial 4:** ![equation](http://latex.codecogs.com/svg.latex?0.000606x%5E4%20&plus;%20-0.029479x%5E3%20&plus;0.41839x%5E2%20&plus;%20-1.677427x%20&plus;%203.868937)
    * **R_Squared:** 0.61451514
* **Plateau Sudden Rise - Binary Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B2426.278978%7D%7B1%20&plus;%20%5Cepsilon%5E%28-1.490227%28x%20--3.662817%29%29%7D%20&plus;%20-2422.928729)
    * **R_Squared:** 0.04409301
* **Constant Decline - Linear:** ![equation](http://latex.codecogs.com/svg.latex?-0.073684x%20&plus;%203.973684)
    * **R_Squared:** 0.03028965
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?0.124187%5Clog_%7B3.718278%7D%28x%29%20&plus;%202.999828)
    * **R_Squared:** 0.00094147

**Plots** :chart_with_upwards_trend:
-----

![mbarta-scr-redesign T11_4](../plots/mbarta-scr-redesign_string_template_T11_4.png)
![mbarta-scr-redesign T9](../plots/mbarta-scr-redesign_string_template_T9.png)
![mbarta-scr-redesign T2](../plots/mbarta-scr-redesign_string_template_T2.png)
![mbarta-scr-redesign T6](../plots/mbarta-scr-redesign_string_template_T6.png)
### <a name="func_with_default_value">Function with Default Value</a>
----
#### Functions
* **Plateau Sudden Rise - Binary Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B2.029124%7D%7B1%20&plus;%20%5Cepsilon%5E%28-1.362021%28x%20-13.503425%29%29%7D%20&plus;%200.986941)
    * **R_Squared:** 0.98512002
* **Instability - Polinomial 3:** ![equation](http://latex.codecogs.com/svg.latex?('-0.001183x%5E3%20&plus;0.046482x%5E2%20&plus;%20-0.382375x%20&plus;%201.641634',))
    * **R_Squared:** 0.91459676
* **Instability - Polinomial 4:** ![equation](http://latex.codecogs.com/svg.latex?-5.6e-05x%5E4%20&plus;%200.001625x%5E3%20&plus;0.000709x%5E2%20&plus;%20-0.115598x%20&plus;%201.247365)
    * **R_Squared:** 0.9228078
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.123478x%20&plus;%200.373188)
    * **R_Squared:** 0.80307999
* **Sudden Rise - Exponential:** ![equation](http://latex.codecogs.com/svg.latex?-58.433122x%5E%7B1.023648%7D%20&plus;%20-3.400609)
    * **R_Squared:** 0.80925963
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?1.199096%5Clog_%7B4.128337%7D%28x%29%20&plus;%200.0)
    * **R_Squared:** 0.57802377

**Plots** :chart_with_upwards_trend:
-----

![mbarta-scr-redesign T9](../plots/mbarta-scr-redesign_func_with_default_value_T9.png)
![mbarta-scr-redesign T11_3](../plots/mbarta-scr-redesign_func_with_default_value_T11_3.png)
![mbarta-scr-redesign T11_4](../plots/mbarta-scr-redesign_func_with_default_value_T11_4.png)
![mbarta-scr-redesign T1](../plots/mbarta-scr-redesign_func_with_default_value_T1.png)
![mbarta-scr-redesign T4](../plots/mbarta-scr-redesign_func_with_default_value_T4.png)
![mbarta-scr-redesign T6](../plots/mbarta-scr-redesign_func_with_default_value_T6.png)
### <a name="smart_cast">Smart Cast</a>
----
#### Functions
* **Plateau Sudden Rise - Binary Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B2.0%7D%7B1%20&plus;%20%5Cepsilon%5E%28-44.833886%28x%20-6.499014%29%29%7D%20&plus;%201.0)
    * **R_Squared:** 1.0
* **Instability - Polinomial 4:** ![equation](http://latex.codecogs.com/svg.latex?0.000655x%5E4%20&plus;%20-0.025469x%5E3%20&plus;0.315234x%5E2%20&plus;%20-1.151855x%20&plus;%202.020362)
    * **R_Squared:** 0.88697706
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?1.071879%5Clog_%7B2.815257%7D%28x%29%20&plus;%200.253111)
    * **R_Squared:** 0.69966753
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.161765x%20&plus;%200.838235)
    * **R_Squared:** 0.6875

**Plots** :chart_with_upwards_trend:
-----

![mbarta-scr-redesign T9](../plots/mbarta-scr-redesign_smart_cast_T9.png)
![mbarta-scr-redesign T11_4](../plots/mbarta-scr-redesign_smart_cast_T11_4.png)
![mbarta-scr-redesign T6](../plots/mbarta-scr-redesign_smart_cast_T6.png)
![mbarta-scr-redesign T1](../plots/mbarta-scr-redesign_smart_cast_T1.png)
### <a name="extension_function">Extension Function</a>
----
#### Functions
* **Plateau Gradual Rise - Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B8.329766%7D%7B1%20&plus;%20%5Cepsilon%5E%28-0.280698%28x%20-9.048582%29%29%7D%20&plus;%200.061651)
    * **R_Squared:** 0.96506593
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.365217x%20&plus;%200.768116)
    * **R_Squared:** 0.90585416
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?3.171757%5Clog_%7B3.718326%7D%28x%29%20&plus;%200.0)
    * **R_Squared:** 0.82271794

**Plots** :chart_with_upwards_trend:
-----

![mbarta-scr-redesign T7](../plots/mbarta-scr-redesign_extension_function_T7.png)
![mbarta-scr-redesign T1](../plots/mbarta-scr-redesign_extension_function_T1.png)
![mbarta-scr-redesign T6](../plots/mbarta-scr-redesign_extension_function_T6.png)
