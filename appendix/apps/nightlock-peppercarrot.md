## nightlock-peppercarrot
----
#### Metrics provided by Detekt
* Number of lines of code 2525
* Number of Kotlin files: 31
* Cyclomatic complexity: 248
* Cyclomatic complexity by thousands of lines: 251 

----
**13** features analyzed

*	<a href="#type_inference">Type Inference</a> 
*	<a href="#lambda">Lambda</a> 
*	<a href="#safe_call">Safe Call</a> 
*	<a href="#when_expr">When expression</a> 
*	<a href="#unsafe_call">Unsafe Call</a> 
*	<a href="#companion_object">Companion Object</a> 
*	<a href="#string_template">String Template</a> 
*	<a href="#singleton">Singleton</a> 
*	<a href="#range_expr">Range Expression</a> 
*	<a href="#smart_cast">Smart Cast</a> 
*	<a href="#data_class">Data Class</a> 
*	<a href="#func_call_with_named_arg">Function call with Named Argument</a> 
*	<a href="#property_delegation">Property Delegation</a> 


### <a name="type_inference">Type Inference</a>
----
#### Functions
* **Instability - Polinomial 3:** ![equation](http://latex.codecogs.com/svg.latex?('0.000109x%5E3%20&plus;-0.037479x%5E2%20&plus;%204.208329x%20&plus;%20-14.737608',))
    * **R_Squared:** 0.93078699
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?30.519154%5Clog_%7B2.911708%7D%28x%29%20&plus;%200.0)
    * **R_Squared:** 0.76767242
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.643468x%20&plus;%2060.667796)
    * **R_Squared:** 0.62081285
* **Plateau Sudden Rise - Binary Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B-34.133354%7D%7B1%20&plus;%20%5Cepsilon%5E%28--191.422131%28x%20-122.220561%29%29%7D%20&plus;%20140.903846)
    * **R_Squared:** 0.14508624

**Plots** :chart_with_upwards_trend:
-----

![nightlock-peppercarrot T11_3](../plots/nightlock-peppercarrot_type_inference_T11_3.png)
![nightlock-peppercarrot T6](../plots/nightlock-peppercarrot_type_inference_T6.png)
![nightlock-peppercarrot T1](../plots/nightlock-peppercarrot_type_inference_T1.png)
![nightlock-peppercarrot T9](../plots/nightlock-peppercarrot_type_inference_T9.png)
### <a name="lambda">Lambda</a>
----
#### Functions
* **Plateau Gradual Rise - Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B-45.865257%7D%7B1%20&plus;%20%5Cepsilon%5E%28--0.765823%28x%20-43.942119%29%29%7D%20&plus;%2056.157643)
    * **R_Squared:** 0.90546549
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.373791x%20&plus;%2012.71229)
    * **R_Squared:** 0.76894219
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?13.358135%5Clog_%7B3.322103%7D%28x%29%20&plus;%200.0)
    * **R_Squared:** 0.64366451

**Plots** :chart_with_upwards_trend:
-----

![nightlock-peppercarrot T7](../plots/nightlock-peppercarrot_lambda_T7.png)
![nightlock-peppercarrot T1](../plots/nightlock-peppercarrot_lambda_T1.png)
![nightlock-peppercarrot T6](../plots/nightlock-peppercarrot_lambda_T6.png)
### <a name="safe_call">Safe Call</a>
----
#### Functions
* **Plateau Gradual Rise - Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B2.953303%7D%7B1%20&plus;%20%5Cepsilon%5E%28-0.382348%28x%20-28.133326%29%29%7D%20&plus;%203.632835)
    * **R_Squared:** 0.55322278
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?1.05231%5Clog_%7B3.718341%7D%28x%29%20&plus;%202.796822)
    * **R_Squared:** 0.2838724
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.008021x%20&plus;%205.431912)
    * **R_Squared:** 0.06645629

**Plots** :chart_with_upwards_trend:
-----

![nightlock-peppercarrot T7](../plots/nightlock-peppercarrot_safe_call_T7.png)
![nightlock-peppercarrot T6](../plots/nightlock-peppercarrot_safe_call_T6.png)
![nightlock-peppercarrot T1](../plots/nightlock-peppercarrot_safe_call_T1.png)
### <a name="when_expr">When expression</a>
----
#### Functions
* **Plateau Gradual Rise - Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B3.752121%7D%7B1%20&plus;%20%5Cepsilon%5E%28-0.190774%28x%20-98.063844%29%29%7D%20&plus;%200.449438)
    * **R_Squared:** 0.90785243
* **Sudden Rise - Exponential:** ![equation](http://latex.codecogs.com/svg.latex?-179.91258x%5E%7B1.006128%7D%20&plus;%20-3.26963)
    * **R_Squared:** 0.79429236
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.03207x%20&plus;%20-0.708458)
    * **R_Squared:** 0.77399747
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?1.010645%5Clog_%7B6.48717%7D%28x%29%20&plus;%200.0)
    * **R_Squared:** 0.28759278

**Plots** :chart_with_upwards_trend:
-----

![nightlock-peppercarrot T7](../plots/nightlock-peppercarrot_when_expr_T7.png)
![nightlock-peppercarrot T4](../plots/nightlock-peppercarrot_when_expr_T4.png)
![nightlock-peppercarrot T1](../plots/nightlock-peppercarrot_when_expr_T1.png)
![nightlock-peppercarrot T6](../plots/nightlock-peppercarrot_when_expr_T6.png)
### <a name="unsafe_call">Unsafe Call</a>
----
#### Functions
* **Sudden Rise - Exponential:** ![equation](http://latex.codecogs.com/svg.latex?-136.239535x%5E%7B1.009146%7D%20&plus;%203.780836)
    * **R_Squared:** 0.55474131
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.074822x%20&plus;%205.728922)
    * **R_Squared:** 0.54051895
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?4.017962%5Clog_%7B3.849854%7D%28x%29%20&plus;%200.0)
    * **R_Squared:** 0.47598225

**Plots** :chart_with_upwards_trend:
-----

![nightlock-peppercarrot T4](../plots/nightlock-peppercarrot_unsafe_call_T4.png)
![nightlock-peppercarrot T1](../plots/nightlock-peppercarrot_unsafe_call_T1.png)
![nightlock-peppercarrot T6](../plots/nightlock-peppercarrot_unsafe_call_T6.png)
### <a name="companion_object">Companion Object</a>
----
#### Functions
* **Plateau Gradual Rise - Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B-15.828802%7D%7B1%20&plus;%20%5Cepsilon%5E%28--0.050289%28x%20-15.011878%29%29%7D%20&plus;%2011.527159)
    * **R_Squared:** 0.92294532
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?3.022444%5Clog_%7B3.685047%7D%28x%29%20&plus;%200.0)
    * **R_Squared:** 0.81211572
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.057789x%20&plus;%204.696928)
    * **R_Squared:** 0.70154384

**Plots** :chart_with_upwards_trend:
-----

![nightlock-peppercarrot T7](../plots/nightlock-peppercarrot_companion_object_T7.png)
![nightlock-peppercarrot T6](../plots/nightlock-peppercarrot_companion_object_T6.png)
![nightlock-peppercarrot T1](../plots/nightlock-peppercarrot_companion_object_T1.png)
### <a name="string_template">String Template</a>
----
#### Functions
* **Plateau Gradual Rise - Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B-28.513168%7D%7B1%20&plus;%20%5Cepsilon%5E%28--0.061242%28x%20-8.293772%29%29%7D%20&plus;%2020.332871)
    * **R_Squared:** 0.60445047
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?5.564729%5Clog_%7B3.685042%7D%28x%29%20&plus;%200.0)
    * **R_Squared:** 0.50338011
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.060337x%20&plus;%2012.574137)
    * **R_Squared:** 0.25857761

**Plots** :chart_with_upwards_trend:
-----

![nightlock-peppercarrot T7](../plots/nightlock-peppercarrot_string_template_T7.png)
![nightlock-peppercarrot T6](../plots/nightlock-peppercarrot_string_template_T6.png)
![nightlock-peppercarrot T1](../plots/nightlock-peppercarrot_string_template_T1.png)
### <a name="singleton">Singleton</a>
----
#### Functions
* **Sudden Decline - Exponential:** ![equation](http://latex.codecogs.com/svg.latex?7.316138x%5E%7B0.949568%7D%20&plus;%200.05465)
    * **R_Squared:** 0.40965454
* **Constant Decline - Linear:** ![equation](http://latex.codecogs.com/svg.latex?-0.002689x%20&plus;%200.447944)
    * **R_Squared:** 0.08549276
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?0.0%5Clog_%7B2167.776907%7D%28x%29%20&plus;%200.212644)
    * **R_Squared:** -0.0

**Plots** :chart_with_upwards_trend:
-----

![nightlock-peppercarrot T5](../plots/nightlock-peppercarrot_singleton_T5.png)
![nightlock-peppercarrot T2](../plots/nightlock-peppercarrot_singleton_T2.png)
![nightlock-peppercarrot T6](../plots/nightlock-peppercarrot_singleton_T6.png)
### <a name="range_expr">Range Expression</a>
----
#### Functions
* **Plateau Gradual Rise - Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B2.585619%7D%7B1%20&plus;%20%5Cepsilon%5E%28-0.105675%28x%20-15.675016%29%29%7D%20&plus;%200.424998)
    * **R_Squared:** 0.91454883
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?1.000346%5Clog_%7B6.600922%7D%28x%29%20&plus;%200.562712)
    * **R_Squared:** 0.76472392
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.008042x%20&plus;%202.085346)
    * **R_Squared:** 0.43137231

**Plots** :chart_with_upwards_trend:
-----

![nightlock-peppercarrot T7](../plots/nightlock-peppercarrot_range_expr_T7.png)
![nightlock-peppercarrot T6](../plots/nightlock-peppercarrot_range_expr_T6.png)
![nightlock-peppercarrot T1](../plots/nightlock-peppercarrot_range_expr_T1.png)
### <a name="smart_cast">Smart Cast</a>
----
#### Functions
* **Plateau Gradual Rise - Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B3.533835%7D%7B1%20&plus;%20%5Cepsilon%5E%28-0.048331%28x%20-27.984893%29%29%7D%20&plus;%200.211953)
    * **R_Squared:** 0.57502633
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?1.001654%5Clog_%7B3.827799%7D%28x%29%20&plus;%200.0)
    * **R_Squared:** 0.52534533
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.022234x%20&plus;%201.427447)
    * **R_Squared:** 0.49587193

**Plots** :chart_with_upwards_trend:
-----

![nightlock-peppercarrot T7](../plots/nightlock-peppercarrot_smart_cast_T7.png)
![nightlock-peppercarrot T6](../plots/nightlock-peppercarrot_smart_cast_T6.png)
![nightlock-peppercarrot T1](../plots/nightlock-peppercarrot_smart_cast_T1.png)
### <a name="data_class">Data Class</a>
----
#### Functions
* **Plateau Sudden Rise - Binary Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B1.0%7D%7B1%20&plus;%20%5Cepsilon%5E%28-43.451387%28x%20-24.499771%29%29%7D%20&plus;%201.0)
    * **R_Squared:** 1.0
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?0.856302%5Clog_%7B15.179713%7D%28x%29%20&plus;%200.569187)
    * **R_Squared:** 0.65777874
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.005312x%20&plus;%201.437351)
    * **R_Squared:** 0.40105263

**Plots** :chart_with_upwards_trend:
-----

![nightlock-peppercarrot T9](../plots/nightlock-peppercarrot_data_class_T9.png)
![nightlock-peppercarrot T6](../plots/nightlock-peppercarrot_data_class_T6.png)
![nightlock-peppercarrot T1](../plots/nightlock-peppercarrot_data_class_T1.png)
### <a name="func_call_with_named_arg">Function call with Named Argument</a>
----
#### Functions
* **Sudden Decline - Exponential:** ![equation](http://latex.codecogs.com/svg.latex?13.59562x%5E%7B0.965359%7D%20&plus;%20-0.078051)
    * **R_Squared:** 0.56339696
* **Constant Decline - Linear:** ![equation](http://latex.codecogs.com/svg.latex?-0.006978x%20&plus;%200.748874)
    * **R_Squared:** 0.35111115
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?0.0%5Clog_%7B2167.776516%7D%28x%29%20&plus;%200.218543)
    * **R_Squared:** -0.0

**Plots** :chart_with_upwards_trend:
-----

![nightlock-peppercarrot T5](../plots/nightlock-peppercarrot_func_call_with_named_arg_T5.png)
![nightlock-peppercarrot T2](../plots/nightlock-peppercarrot_func_call_with_named_arg_T2.png)
![nightlock-peppercarrot T6](../plots/nightlock-peppercarrot_func_call_with_named_arg_T6.png)
### <a name="property_delegation">Property Delegation</a>
----
#### Functions
* **Plateau Gradual Rise - Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B3.530342%7D%7B1%20&plus;%20%5Cepsilon%5E%28-0.384403%28x%20-18.560382%29%29%7D%20&plus;%204.069399)
    * **R_Squared:** 0.83516081
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?1.286054%5Clog_%7B3.718382%7D%28x%29%20&plus;%203.321695)
    * **R_Squared:** 0.53735384
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.017496x%20&plus;%205.996535)
    * **R_Squared:** 0.24827664

**Plots** :chart_with_upwards_trend:
-----

![nightlock-peppercarrot T7](../plots/nightlock-peppercarrot_property_delegation_T7.png)
![nightlock-peppercarrot T6](../plots/nightlock-peppercarrot_property_delegation_T6.png)
![nightlock-peppercarrot T1](../plots/nightlock-peppercarrot_property_delegation_T1.png)
