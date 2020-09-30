## saschpe-poker
----
#### Metrics provided by Detekt
* Number of lines of code 1120
* Number of Kotlin files: 12
* Cyclomatic complexity: 128
* Cyclomatic complexity by thousands of lines: 252 

----
**7** features analyzed

*	<a href="#type_inference">Type Inference</a> 
*	<a href="#lambda">Lambda</a> 
*	<a href="#safe_call">Safe Call</a> 
*	<a href="#when_expr">When expression</a> 
*	<a href="#unsafe_call">Unsafe Call</a> 
*	<a href="#companion_object">Companion Object</a> 
*	<a href="#singleton">Singleton</a> 


### <a name="type_inference">Type Inference</a>
----
#### Functions
* **Instability - Polinomial 4:** ![equation](http://latex.codecogs.com/svg.latex?-6.4e-05x%5E4%20&plus;%200.00867x%5E3%20&plus;-0.385544x%5E2%20&plus;%206.548808x%20&plus;%200.229117)
    * **R_Squared:** 0.84632782
* **Instability - Polinomial 3:** ![equation](http://latex.codecogs.com/svg.latex?('0.000804x%5E3%20&plus;-0.075363x%5E2%20&plus;%202.261985x%20&plus;%2014.274164',))
    * **R_Squared:** 0.63227565
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?9.141512%5Clog_%7B3.358578%7D%28x%29%20&plus;%2011.728422)
    * **R_Squared:** 0.61936986
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.343623x%20&plus;%2024.969492)
    * **R_Squared:** 0.48281451
* **Plateau Sudden Rise - Binary Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B-8.023569%7D%7B1%20&plus;%20%5Cepsilon%5E%28--547.463282%28x%20-27.058238%29%29%7D%20&plus;%2039.060606)
    * **R_Squared:** 0.21723264

**Plots** :chart_with_upwards_trend:
-----

![saschpe-poker T11_4](../plots/saschpe-poker_type_inference_T11_4.png)
![saschpe-poker T11_3](../plots/saschpe-poker_type_inference_T11_3.png)
![saschpe-poker T6](../plots/saschpe-poker_type_inference_T6.png)
![saschpe-poker T1](../plots/saschpe-poker_type_inference_T1.png)
![saschpe-poker T9](../plots/saschpe-poker_type_inference_T9.png)
### <a name="lambda">Lambda</a>
----
#### Functions
* **Sudden Decline - Exponential:** ![equation](http://latex.codecogs.com/svg.latex?14.239552x%5E%7B0.881722%7D%20&plus;%202.058569)
    * **R_Squared:** 0.59799444
* **Constant Decline - Linear:** ![equation](http://latex.codecogs.com/svg.latex?-0.053862x%20&plus;%204.392208)
    * **R_Squared:** 0.28134696
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?0.0%5Clog_%7B154.336617%7D%28x%29%20&plus;%202.857143)
    * **R_Squared:** -0.0

**Plots** :chart_with_upwards_trend:
-----

![saschpe-poker T5](../plots/saschpe-poker_lambda_T5.png)
![saschpe-poker T2](../plots/saschpe-poker_lambda_T2.png)
![saschpe-poker T6](../plots/saschpe-poker_lambda_T6.png)
### <a name="safe_call">Safe Call</a>
----
#### Functions
* **Instability - Polinomial 3:** ![equation](http://latex.codecogs.com/svg.latex?('0.000248x%5E3%20&plus;-0.024202x%5E2%20&plus;%200.774328x%20&plus;%20-0.996102',))
    * **R_Squared:** 0.43591766
* **Plateau Gradual Rise - Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B-5.089445%7D%7B1%20&plus;%20%5Cepsilon%5E%28--52.846619%28x%20-13.962895%29%29%7D%20&plus;%207.627907)
    * **R_Squared:** 0.40312538
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?2.722148%5Clog_%7B3.655598%7D%28x%29%20&plus;%200.0)
    * **R_Squared:** 0.35893903
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.117903x%20&plus;%203.036967)
    * **R_Squared:** 0.33398411

**Plots** :chart_with_upwards_trend:
-----

![saschpe-poker T11_3](../plots/saschpe-poker_safe_call_T11_3.png)
![saschpe-poker T7](../plots/saschpe-poker_safe_call_T7.png)
![saschpe-poker T6](../plots/saschpe-poker_safe_call_T6.png)
![saschpe-poker T1](../plots/saschpe-poker_safe_call_T1.png)
### <a name="when_expr">When expression</a>
----
#### Functions
* **Sudden Rise - Exponential:** ![equation](http://latex.codecogs.com/svg.latex?42.49981x%5E%7B1.071597%7D%20&plus;%206.719261)
    * **R_Squared:** 0.81965744
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.039085x%20&plus;%206.287594)
    * **R_Squared:** 0.65658993
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?0.794496%5Clog_%7B3.71391%7D%28x%29%20&plus;%205.547168)
    * **R_Squared:** 0.46090885

**Plots** :chart_with_upwards_trend:
-----

![saschpe-poker T4](../plots/saschpe-poker_when_expr_T4.png)
![saschpe-poker T1](../plots/saschpe-poker_when_expr_T1.png)
![saschpe-poker T6](../plots/saschpe-poker_when_expr_T6.png)
### <a name="unsafe_call">Unsafe Call</a>
----
#### Functions
* **Instability - Polinomial 4:** ![equation](http://latex.codecogs.com/svg.latex?-7e-05x%5E4%20&plus;%200.007193x%5E3%20&plus;-0.181909x%5E2%20&plus;%20-1.024621x%20&plus;%2052.313444)
    * **R_Squared:** 0.74282883
* **Instability - Polinomial 3:** ![equation](http://latex.codecogs.com/svg.latex?('-0.000938x%5E3%20&plus;0.123062x%5E2%20&plus;%20-5.03622x%20&plus;%2064.855282',))
    * **R_Squared:** 0.70834231
* **Sudden Decline - Exponential:** ![equation](http://latex.codecogs.com/svg.latex?45.860336x%5E%7B0.912789%7D%20&plus;%20-1.362311)
    * **R_Squared:** 0.65643375
* **Constant Decline - Linear:** ![equation](http://latex.codecogs.com/svg.latex?-0.722777x%20&plus;%2031.592105)
    * **R_Squared:** 0.39641825
* **Plateau Sudden Decline - Binary Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B-30.648148%7D%7B1%20&plus;%20%5Cepsilon%5E%28-272.400698%28x%20-3.442786%29%29%7D%20&plus;%2039.666666)
    * **R_Squared:** 0.13130591
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?0.0%5Clog_%7B41.513482%7D%28x%29%20&plus;%2010.631579)
    * **R_Squared:** -0.0

**Plots** :chart_with_upwards_trend:
-----

![saschpe-poker T11_4](../plots/saschpe-poker_unsafe_call_T11_4.png)
![saschpe-poker T11_3](../plots/saschpe-poker_unsafe_call_T11_3.png)
![saschpe-poker T5](../plots/saschpe-poker_unsafe_call_T5.png)
![saschpe-poker T2](../plots/saschpe-poker_unsafe_call_T2.png)
![saschpe-poker T10](../plots/saschpe-poker_unsafe_call_T10.png)
![saschpe-poker T6](../plots/saschpe-poker_unsafe_call_T6.png)
### <a name="companion_object">Companion Object</a>
----
#### Functions
* **Plateau Sudden Rise - Binary Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B1.026317%7D%7B1%20&plus;%20%5Cepsilon%5E%28-14.528944%28x%20-39.250347%29%29%7D%20&plus;%203.973684)
    * **R_Squared:** 0.93010076
* **Sudden Rise - Exponential:** ![equation](http://latex.codecogs.com/svg.latex?44.232976x%5E%7B1.041109%7D%20&plus;%203.629903)
    * **R_Squared:** 0.74006808
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.024566x%20&plus;%203.58584)
    * **R_Squared:** 0.66837953
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?0.499256%5Clog_%7B3.717984%7D%28x%29%20&plus;%203.121692)
    * **R_Squared:** 0.46820751

**Plots** :chart_with_upwards_trend:
-----

![saschpe-poker T9](../plots/saschpe-poker_companion_object_T9.png)
![saschpe-poker T4](../plots/saschpe-poker_companion_object_T4.png)
![saschpe-poker T1](../plots/saschpe-poker_companion_object_T1.png)
![saschpe-poker T6](../plots/saschpe-poker_companion_object_T6.png)
### <a name="singleton">Singleton</a>
----
#### Functions
* **Plateau Sudden Rise - Binary Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B2.0%7D%7B1%20&plus;%20%5Cepsilon%5E%28-46.163301%28x%20-42.500243%29%29%7D%20&plus;%201.0)
    * **R_Squared:** 1.0
* **Sudden Rise - Exponential:** ![equation](http://latex.codecogs.com/svg.latex?37.912011x%5E%7B1.051652%7D%20&plus;%200.618133)
    * **R_Squared:** 0.76351175
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.042012x%20&plus;%200.318644)
    * **R_Squared:** 0.63017505
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?1.046065%5Clog_%7B7.609836%7D%28x%29%20&plus;%200.0)
    * **R_Squared:** 0.33080155

**Plots** :chart_with_upwards_trend:
-----

![saschpe-poker T9](../plots/saschpe-poker_singleton_T9.png)
![saschpe-poker T4](../plots/saschpe-poker_singleton_T4.png)
![saschpe-poker T1](../plots/saschpe-poker_singleton_T1.png)
![saschpe-poker T6](../plots/saschpe-poker_singleton_T6.png)
