## jpeddicord-speedofsound
----
#### Metrics provided by Detekt
* Number of lines of code 1940
* Number of Kotlin files: 13
* Cyclomatic complexity: 195
* Cyclomatic complexity by thousands of lines: 236 

----
**6** features analyzed

*	<a href="#type_inference">Type Inference</a> 
*	<a href="#lambda">Lambda</a> 
*	<a href="#safe_call">Safe Call</a> 
*	<a href="#when_expr">When expression</a> 
*	<a href="#unsafe_call">Unsafe Call</a> 
*	<a href="#string_template">String Template</a> 


### <a name="type_inference">Type Inference</a>
----
#### Functions
* **Instability - Polinomial 3:** ![equation](http://latex.codecogs.com/svg.latex?('0.009179x%5E3%20&plus;-0.207273x%5E2%20&plus;%200.751066x%20&plus;%20172.197802',))
    * **R_Squared:** 0.72510844
* **Instability - Polinomial 4:** ![equation](http://latex.codecogs.com/svg.latex?-0.000666x%5E4%20&plus;%200.031826x%5E3%20&plus;-0.460098x%5E2%20&plus;%201.776641x%20&plus;%20171.091346)
    * **R_Squared:** 0.73097966
* **Sudden Decline - Exponential:** ![equation](http://latex.codecogs.com/svg.latex?27.751146x%5E%7B0.919793%7D%20&plus;%20164.432395)
    * **R_Squared:** 0.6394039
* **Constant Decline - Linear:** ![equation](http://latex.codecogs.com/svg.latex?-0.433824x%20&plus;%20173.5)
    * **R_Squared:** 0.61270109
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?0.0%5Clog_%7B613.297473%7D%28x%29%20&plus;%20169.812507)
    * **R_Squared:** -0.0

**Plots** :chart_with_upwards_trend:
-----

![jpeddicord-speedofsound T11_3](../plots/jpeddicord-speedofsound_type_inference_T11_3.png)
![jpeddicord-speedofsound T11_4](../plots/jpeddicord-speedofsound_type_inference_T11_4.png)
![jpeddicord-speedofsound T5](../plots/jpeddicord-speedofsound_type_inference_T5.png)
![jpeddicord-speedofsound T2](../plots/jpeddicord-speedofsound_type_inference_T2.png)
![jpeddicord-speedofsound T6](../plots/jpeddicord-speedofsound_type_inference_T6.png)
### <a name="lambda">Lambda</a>
----
#### Functions
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.161765x%20&plus;%2012.625)
    * **R_Squared:** 0.88970588
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?1.697034%5Clog_%7B6.627985%7D%28x%29%20&plus;%2012.279915)
    * **R_Squared:** 0.75210884

**Plots** :chart_with_upwards_trend:
-----

![jpeddicord-speedofsound T1](../plots/jpeddicord-speedofsound_lambda_T1.png)
![jpeddicord-speedofsound T6](../plots/jpeddicord-speedofsound_lambda_T6.png)
### <a name="safe_call">Safe Call</a>
----
#### Functions
* **Plateau Sudden Rise - Binary Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B5.0%7D%7B1%20&plus;%20%5Cepsilon%5E%28-45.952786%28x%20-4.499146%29%29%7D%20&plus;%201.0)
    * **R_Squared:** 1.0
* **Instability - Polinomial 4:** ![equation](http://latex.codecogs.com/svg.latex?0.010198x%5E4%20&plus;%20-0.265152x%5E3%20&plus;2.233392x%5E2%20&plus;%20-5.976107x%20&plus;%205.242424)
    * **R_Squared:** 0.8974359
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?3.50186%5Clog_%7B3.714946%7D%28x%29%20&plus;%200.0)
    * **R_Squared:** 0.71421526
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.636364x%20&plus;%200.363636)
    * **R_Squared:** 0.7

**Plots** :chart_with_upwards_trend:
-----

![jpeddicord-speedofsound T9](../plots/jpeddicord-speedofsound_safe_call_T9.png)
![jpeddicord-speedofsound T11_4](../plots/jpeddicord-speedofsound_safe_call_T11_4.png)
![jpeddicord-speedofsound T6](../plots/jpeddicord-speedofsound_safe_call_T6.png)
![jpeddicord-speedofsound T1](../plots/jpeddicord-speedofsound_safe_call_T1.png)
### <a name="when_expr">When expression</a>
----
#### Functions
* **Plateau Sudden Rise - Binary Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B1.0%7D%7B1%20&plus;%20%5Cepsilon%5E%28-41.311583%28x%20-9.50022%29%29%7D%20&plus;%204.0)
    * **R_Squared:** 1.0
* **Instability - Polinomial 4:** ![equation](http://latex.codecogs.com/svg.latex?-0.000234x%5E4%20&plus;%200.005956x%5E3%20&plus;-0.03394x%5E2%20&plus;%200.031136x%20&plus;%204.033654)
    * **R_Squared:** 0.87354132
* **Instability - Polinomial 3:** ![equation](http://latex.codecogs.com/svg.latex?('-0.002014x%5E3%20&plus;0.055043x%5E2%20&plus;%20-0.329821x%20&plus;%204.423077',))
    * **R_Squared:** 0.85425101
* **Sudden Rise - Exponential:** ![equation](http://latex.codecogs.com/svg.latex?-1.469159x%5E%7B1.054978%7D%20&plus;%202.680158)
    * **R_Squared:** 0.75445903
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.092647x%20&plus;%203.65)
    * **R_Squared:** 0.74117647
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?0.625766%5Clog_%7B3.718448%7D%28x%29%20&plus;%203.52409)
    * **R_Squared:** 0.53863187

**Plots** :chart_with_upwards_trend:
-----

![jpeddicord-speedofsound T9](../plots/jpeddicord-speedofsound_when_expr_T9.png)
![jpeddicord-speedofsound T11_4](../plots/jpeddicord-speedofsound_when_expr_T11_4.png)
![jpeddicord-speedofsound T11_3](../plots/jpeddicord-speedofsound_when_expr_T11_3.png)
![jpeddicord-speedofsound T4](../plots/jpeddicord-speedofsound_when_expr_T4.png)
![jpeddicord-speedofsound T1](../plots/jpeddicord-speedofsound_when_expr_T1.png)
![jpeddicord-speedofsound T6](../plots/jpeddicord-speedofsound_when_expr_T6.png)
### <a name="unsafe_call">Unsafe Call</a>
----
#### Functions
* **Instability - Polinomial 4:** ![equation](http://latex.codecogs.com/svg.latex?-0.001457x%5E4%20&plus;%200.05375x%5E3%20&plus;-0.625398x%5E2%20&plus;%201.985684x%20&plus;%2052.485577)
    * **R_Squared:** 0.92043278
* **Sudden Decline - Exponential:** ![equation](http://latex.codecogs.com/svg.latex?17.002408x%5E%7B0.879854%7D%20&plus;%2047.298875)
    * **R_Squared:** 0.84112119
* **Constant Decline - Linear:** ![equation](http://latex.codecogs.com/svg.latex?-0.416176x%20&plus;%2054.35)
    * **R_Squared:** 0.77041989
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?0.0%5Clog_%7B24.94464%7D%28x%29%20&plus;%2050.812501)
    * **R_Squared:** -0.0

**Plots** :chart_with_upwards_trend:
-----

![jpeddicord-speedofsound T11_4](../plots/jpeddicord-speedofsound_unsafe_call_T11_4.png)
![jpeddicord-speedofsound T5](../plots/jpeddicord-speedofsound_unsafe_call_T5.png)
![jpeddicord-speedofsound T2](../plots/jpeddicord-speedofsound_unsafe_call_T2.png)
![jpeddicord-speedofsound T6](../plots/jpeddicord-speedofsound_unsafe_call_T6.png)
### <a name="string_template">String Template</a>
----
#### Functions
* **Instability - Polinomial 3:** ![equation](http://latex.codecogs.com/svg.latex?('-0.006251x%5E3%20&plus;0.166766x%5E2%20&plus;%20-0.962729x%20&plus;%209.192308',))
    * **R_Squared:** 0.90066771
* **Instability - Polinomial 4:** ![equation](http://latex.codecogs.com/svg.latex?-0.000469x%5E4%20&plus;%200.00969x%5E3%20&plus;-0.011201x%5E2%20&plus;%20-0.240816x%20&plus;%208.413462)
    * **R_Squared:** 0.90966985
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.279412x%20&plus;%207.0)
    * **R_Squared:** 0.78649237
* **Sudden Rise - Exponential:** ![equation](http://latex.codecogs.com/svg.latex?-49.482378x%5E%7B1.036115%7D%20&plus;%201.446794)
    * **R_Squared:** 0.79267235
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?1.906275%5Clog_%7B3.691994%7D%28x%29%20&plus;%206.577262)
    * **R_Squared:** 0.58955049

**Plots** :chart_with_upwards_trend:
-----

![jpeddicord-speedofsound T11_3](../plots/jpeddicord-speedofsound_string_template_T11_3.png)
![jpeddicord-speedofsound T11_4](../plots/jpeddicord-speedofsound_string_template_T11_4.png)
![jpeddicord-speedofsound T1](../plots/jpeddicord-speedofsound_string_template_T1.png)
![jpeddicord-speedofsound T4](../plots/jpeddicord-speedofsound_string_template_T4.png)
![jpeddicord-speedofsound T6](../plots/jpeddicord-speedofsound_string_template_T6.png)
